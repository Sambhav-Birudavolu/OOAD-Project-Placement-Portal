package com.pesuplacements.repository;

import com.pesuplacements.entity.JobOpenings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobOpeningsRepository extends JpaRepository<JobOpenings, Long> {
}
