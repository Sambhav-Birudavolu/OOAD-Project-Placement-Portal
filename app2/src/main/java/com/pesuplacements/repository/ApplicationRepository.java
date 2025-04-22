package com.pesuplacements.repository;

import com.pesuplacements.entity.Application;
import com.pesuplacements.entity.ApplicationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, ApplicationId> {
    boolean existsBySrnAndJobId(String srn, Long jobId);

    List<Application> findByJobId(Long jobId);
}
