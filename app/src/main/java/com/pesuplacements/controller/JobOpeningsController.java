package com.pesuplacements.controller;

import com.pesuplacements.entity.JobOpenings;
import com.pesuplacements.repository.JobOpeningsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JobOpeningsController {

    @Autowired
    private JobOpeningsRepository jobOpeningsRepository;

    // Existing endpoint to fetch all job openings
    @GetMapping("/api/job-openings")
    public List<JobOpenings> getJobOpenings() {
        return jobOpeningsRepository.findAll();
    }

    // New endpoint to fetch company name of the logged-in user
    @GetMapping("/api/company/job-openings/company-name")
    public String getCompanyName(Authentication authentication) {
        // Return the company name from the Authentication object
        return authentication.getName(); // This gives the username, which is the company name in your case
    }
}
