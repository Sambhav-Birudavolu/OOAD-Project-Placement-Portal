package com.pesuplacements.repository;

import com.pesuplacements.entity.StudentJobOpenings;
import com.pesuplacements.entity.StudentJobOpeningsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface StudentJobOpeningsRepository extends JpaRepository<StudentJobOpenings, StudentJobOpeningsId> {

    boolean existsBySrnAndJobId(String srn, Long jobId);

    
    List<StudentJobOpenings> findBySrn(String srn);

    @Query("""
        SELECT s.jobId, s.jobTitle, s.companyName, s.jobDescription, j.isOpen
        FROM StudentJobOpenings s
        JOIN JobOpenings j ON s.jobId = j.jobID
        WHERE s.srn = :srn
    """)
    List<Object[]> findAssignedJobsWithStatusBySrn(@Param("srn") String srn);
    
}
