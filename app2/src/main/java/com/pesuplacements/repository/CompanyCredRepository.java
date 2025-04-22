package com.pesuplacements.repository;

import com.pesuplacements.entity.CompanyCred; 
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyCredRepository extends JpaRepository<CompanyCred, String> {  
    Optional<CompanyCred> findByCompanyName(String companyName);  
}
