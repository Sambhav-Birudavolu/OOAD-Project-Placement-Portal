package com.pesuplacements.services;

import com.pesuplacements.entity.StudentCred;
import com.pesuplacements.factory.StudentCredFactory; 
import com.pesuplacements.repository.StudentCredRepository;
import com.pesuplacements.util.CustomStudentDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomStudentDetailsService implements UserDetailsService {

    private final StudentCredRepository studentCredRepository;

    @Autowired
    public CustomStudentDetailsService(StudentCredRepository studentCredRepository) {
        this.studentCredRepository = studentCredRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String srn) throws UsernameNotFoundException {
       
        StudentCred studentCred = studentCredRepository.findById(srn)
                .orElseThrow(() -> new UsernameNotFoundException("Could not find student with SRN " + srn));
        
        
        return new CustomStudentDetails(studentCred);
    }

    public StudentCred createStudent(String srn, String password) {
       
        return StudentCredFactory.create(srn, password);
    }
}
