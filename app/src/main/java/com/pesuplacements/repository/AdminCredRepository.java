package com.pesuplacements.repository;

import com.pesuplacements.entity.AdminCred;  
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminCredRepository extends JpaRepository<AdminCred, String> {  
    Optional<AdminCred> findByAid(String aid);  
}
