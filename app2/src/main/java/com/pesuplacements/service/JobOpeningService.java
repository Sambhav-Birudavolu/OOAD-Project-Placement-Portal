package com.pesuplacements.services;

import com.pesuplacements.entity.JobOpenings;
import com.pesuplacements.repository.JobOpeningsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobOpeningService {

    @Autowired
    private JobOpeningsRepository jobOpeningsRepository;

    public JobOpenings createJobOpening(JobOpenings jobOpenings) {
        // Save the job opening to the repository
        return jobOpeningsRepository.save(jobOpenings);
    }
}
