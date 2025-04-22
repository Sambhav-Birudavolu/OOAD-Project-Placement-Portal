package com.pesuplacements.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.pesuplacements.repository.StudentDetailRepository;
import com.pesuplacements.repository.StudentJobOpeningsRepository;
import com.pesuplacements.repository.JobOpeningsRepository;
import com.pesuplacements.entity.JobOpenings;
import com.pesuplacements.entity.StudentDetail;
import com.pesuplacements.entity.StudentJobOpenings;

import java.util.List;

@RestController
@RequestMapping("/api")
public class JobForwardingController {

    @Autowired
    private StudentDetailRepository studentDetailRepository;

    @Autowired
    private StudentJobOpeningsRepository studentJobOpeningsRepository;

    @Autowired
    private JobOpeningsRepository jobOpeningsRepository;

    @PostMapping("/forward-job/{jobID}")
    public String forwardJob(@PathVariable Long jobID) {
        System.out.println("JobForwardingController reached for jobID: " + jobID);
    
        JobOpenings job = jobOpeningsRepository.findById(jobID).orElse(null);
        if (job == null || !job.getIsOpen()) {
            System.out.println("Job with ID " + jobID + " is either not found or is closed.");
            return "Job is either not found or is already closed.";
        }
    
        System.out.println("Forwarding job: " + job.getJobTitle() + " at company: " + job.getCompanyName());
    
        List<StudentDetail> students = studentDetailRepository.findAll();
        int forwardedCount = 0;
        int skippedDueToPenalty = 0;
    
        for (StudentDetail student : students) {
            boolean isEligible = student.getBranch().equals(job.getPreferredBranch())
                                 && student.getGpa() >= job.getMinGpa();
    
            if (!isEligible) continue;
    
            if (studentJobOpeningsRepository.existsBySrnAndJobId(student.getSrn(), jobID)) {
                continue; 
            }
    
            if (student.getPenalty() > 0) {
                student.setPenalty(student.getPenalty() - 1);
                studentDetailRepository.save(student);
                System.out.println("Penalty present for " + student.getSrn() + ", deducted 1, not forwarded.");
                skippedDueToPenalty++;
                continue;
            }
    
            
            System.out.println("Forwarding job to student: " + student.getSrn());
    
            StudentJobOpenings studentJobOpening = new StudentJobOpenings();
            studentJobOpening.setSrn(student.getSrn());
            studentJobOpening.setJobId(jobID);
            studentJobOpening.setJobTitle(job.getJobTitle());
            studentJobOpening.setCompanyName(job.getCompanyName());
            studentJobOpening.setJobDescription(job.getJobDescription());
    
            studentJobOpeningsRepository.save(studentJobOpening);
            forwardedCount++;
        }
    
        System.out.println("Job successfully forwarded to " + forwardedCount + " students. " +
                           skippedDueToPenalty + " skipped due to penalties.");
        
        return "Job forwarded to " + forwardedCount + " students. " +
               skippedDueToPenalty + " had penalties and were skipped.";
    }
    
}
