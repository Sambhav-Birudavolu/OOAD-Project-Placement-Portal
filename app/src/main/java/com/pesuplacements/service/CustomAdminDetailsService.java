package com.pesuplacements.services;

import com.pesuplacements.entity.AdminCred;  
import com.pesuplacements.repository.AdminCredRepository;  
import com.pesuplacements.util.CustomAdminDetails;  

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomAdminDetailsService implements UserDetailsService {

    private final AdminCredRepository adminCredRepository;  

    @Autowired
    public CustomAdminDetailsService(AdminCredRepository adminCredRepository) {
        this.adminCredRepository = adminCredRepository; 
    }

    @Override
    public UserDetails loadUserByUsername(String aid) throws UsernameNotFoundException {
        
        AdminCred adminCred = adminCredRepository.findById(aid)
                .orElseThrow(() -> new UsernameNotFoundException("Could not find student with AID " + aid));
        
        
        return new CustomAdminDetails(adminCred);
    }

}
