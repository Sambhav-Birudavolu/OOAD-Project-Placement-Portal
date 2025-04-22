package com.pesuplacements.controller.admin;

import com.pesuplacements.entity.WithdrawalRequest;
import com.pesuplacements.entity.WithdrawalRequestId;
import com.pesuplacements.repository.WithdrawalRequestRepository;

import com.pesuplacements.entity.ApplicationId;
import com.pesuplacements.entity.StudentJobOpeningsId;

import com.pesuplacements.repository.ApplicationRepository;
import com.pesuplacements.repository.StudentJobOpeningsRepository;
import com.pesuplacements.repository.StudentDetailRepository;
import com.pesuplacements.entity.StudentDetail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminWithdrawController {

    @Autowired
    private WithdrawalRequestRepository withdrawalRequestRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private StudentJobOpeningsRepository studentJobOpeningsRepository;

    @Autowired
    private StudentDetailRepository studentDetailRepository;

    @GetMapping("/withdraw-requests")
    public List<WithdrawalRequest> getWithdrawRequests() {
        return withdrawalRequestRepository.findAll();
    }

    @PostMapping("/accept-withdraw")
    public ResponseEntity<?> acceptWithdraw(@RequestBody WithdrawalRequestId id) {
        
        withdrawalRequestRepository.deleteById(id);
        applicationRepository.deleteById(new ApplicationId(id.getSrn(), id.getJobId()));
        studentJobOpeningsRepository.deleteById(new StudentJobOpeningsId(id.getSrn(), id.getJobId()));

        
        studentDetailRepository.findById(id.getSrn()).ifPresent(student -> {
            student.setPenalty(student.getPenalty() + 1);
            studentDetailRepository.save(student); 
        });

        return ResponseEntity.ok().build();
    }
}
