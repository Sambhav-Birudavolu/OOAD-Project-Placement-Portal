package com.pesuplacements.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Map;

import com.pesuplacements.entity.Application;
import com.pesuplacements.repository.ApplicationRepository;

@RestController
@RequestMapping("/api/student")
public class StudentApplicationController {

    @Autowired
    private ApplicationRepository applicationRepository;

    @PostMapping("/apply")
    public ResponseEntity<?> applyToJob(@RequestBody Map<String, Long> body) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String srn = authentication.getName();
        Long jobId = body.get("jobId");

        boolean alreadyApplied = applicationRepository.existsBySrnAndJobId(srn, jobId);
        if (alreadyApplied) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        Application application = new Application();
        application.setSrn(srn);
        application.setJobId(jobId);
        application.setRoundsPassed(0);
        applicationRepository.save(application);

        return ResponseEntity.ok().build();
    }
}
