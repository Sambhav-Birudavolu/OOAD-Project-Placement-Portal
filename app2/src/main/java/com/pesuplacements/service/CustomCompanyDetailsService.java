package com.pesuplacements.services;

import com.pesuplacements.entity.CompanyCred; 
import com.pesuplacements.repository.CompanyCredRepository; 
import com.pesuplacements.util.CustomCompanyDetails; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomCompanyDetailsService implements UserDetailsService {

    private final CompanyCredRepository companyCredRepository; 

    @Autowired
    public CustomCompanyDetailsService(CompanyCredRepository companyCredRepository) {
        this.companyCredRepository = companyCredRepository; 
    }

    @Override
    public UserDetails loadUserByUsername(String companyName) throws UsernameNotFoundException {
        
        CompanyCred companyCred = companyCredRepository.findByCompanyName(companyName)
                .orElseThrow(() -> new UsernameNotFoundException("Could not find company with name " + companyName));
        
        
        return new CustomCompanyDetails(companyCred);
    }
}
