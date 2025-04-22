package com.pesuplacements.repository;

import com.pesuplacements.entity.JobOpenings;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JobOpeningsRepository extends JpaRepository<JobOpenings, Long> {
    List<JobOpenings> findByCompanyName(String companyName);
}
