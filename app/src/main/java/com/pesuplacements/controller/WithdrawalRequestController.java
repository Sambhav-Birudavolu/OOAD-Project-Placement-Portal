package com.pesuplacements.controller;

import com.pesuplacements.entity.WithdrawalRequest;
import com.pesuplacements.repository.WithdrawalRequestRepository;

import com.pesuplacements.entity.WithdrawalRequestId;
import com.pesuplacements.entity.ApplicationId;
import com.pesuplacements.repository.ApplicationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
public class WithdrawalRequestController {

    @Autowired
    private WithdrawalRequestRepository withdrawalRequestRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @PostMapping("/withdraw")
    public ResponseEntity<?> requestWithdraw(@RequestBody JobWithdrawRequest jobRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String srn = authentication.getName();

        WithdrawalRequestId id = new WithdrawalRequestId(srn, jobRequest.getJobId());
        ApplicationId applicationId = new ApplicationId(srn, jobRequest.getJobId());

        
        if (!applicationRepository.existsById(applicationId)) {
            return ResponseEntity.badRequest().body("You have not applied for this job.");
        }

        
        if (withdrawalRequestRepository.existsById(id)) {
            return ResponseEntity.status(409).body("Already requested withdrawal.");
        }

        
        WithdrawalRequest request = new WithdrawalRequest();
        request.setId(id);
        withdrawalRequestRepository.save(request);

        return ResponseEntity.ok("Withdraw request submitted.");
    }

    public static class JobWithdrawRequest {
        private Long jobId;
        public Long getJobId() { return jobId; }
        public void setJobId(Long jobId) { this.jobId = jobId; }
    }
}
