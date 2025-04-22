package com.pesuplacements.repository;

import com.pesuplacements.entity.StudentCred;  
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentCredRepository extends JpaRepository<StudentCred, String> { 
    Optional<StudentCred> findBySrn(String srn); 
}
