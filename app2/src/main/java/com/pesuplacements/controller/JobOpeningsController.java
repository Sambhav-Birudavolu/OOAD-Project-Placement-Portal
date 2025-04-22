package com.pesuplacements.controller;

import com.pesuplacements.entity.Application;
import com.pesuplacements.entity.JobOpenings;
import com.pesuplacements.repository.ApplicationRepository;
import com.pesuplacements.repository.JobOpeningsRepository;
import com.pesuplacements.services.JobOpeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/company")
public class JobOpeningsController {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private JobOpeningsRepository jobOpeningsRepository;

    @Autowired
    private JobOpeningService jobOpeningService;

    // Get all job openings for the logged-in company
    @GetMapping("/job-openings")
    public ResponseEntity<List<JobOpenings>> getCompanyJobs() {
        // Fetch the company name directly from the authentication object
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String companyName = authentication.getName();  // getName() will return the company name

        List<JobOpenings> jobs = jobOpeningsRepository.findByCompanyName(companyName);
        return ResponseEntity.ok(jobs);
    }

    // Get all applicants for a specific job
    @GetMapping("/applicants/{jobId}")
    public List<Application> getApplicants(@PathVariable Long jobId) {
        return applicationRepository.findByJobId(jobId);
    }

    // Create a new job opening under the authenticated company
    @PostMapping("/job-openings")
    public ResponseEntity<JobOpenings> createJobOpening(@Valid @RequestBody JobOpenings jobOpenings) {
        // Fetch the company name directly from the authentication object
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String companyName = authentication.getName();  // getName() will return the company name
        jobOpenings.setCompanyName(companyName); // Ensure the job is linked to the correct company

        // Create and save the job opening
        JobOpenings createdJob = jobOpeningService.createJobOpening(jobOpenings);
        return ResponseEntity.status(201).body(createdJob);  // Return status 201 for created resource
    }

    // Shortlist applicants for a specific job
    @PostMapping("/shortlist/{jobId}")
    public void shortlistApplicants(@PathVariable Long jobId, @RequestBody Map<String, List<String>> payload) {
        List<String> srns = payload.get("srns");

        for (String srn : srns) {
            Application app = applicationRepository.findByJobId(jobId)
                    .stream()
                    .filter(a -> a.getSrn().equals(srn))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Application not found for SRN: " + srn));
            app.setRoundsPassed(app.getRoundsPassed() + 1);
            applicationRepository.save(app);
        }

        JobOpenings job = jobOpeningsRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));
        job.setNumberOfRoundsLeft(job.getNumberOfRoundsLeft() - 1);
        job.setIsOpen(false); 
        jobOpeningsRepository.save(job);
    }
    // Endpoint to fetch the logged-in company's name
    @GetMapping(value = "/job-openings/company-name", produces = "text/plain")
    public String getCompanyName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();  // This will return the logged-in username (company name)
    }

}
