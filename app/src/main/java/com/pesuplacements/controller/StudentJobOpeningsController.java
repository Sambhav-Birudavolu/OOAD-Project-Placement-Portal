package com.pesuplacements.controller;

import com.pesuplacements.dto.AssignedJobDTO;
import com.pesuplacements.repository.StudentJobOpeningsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/student")
public class StudentJobOpeningsController {

    @Autowired
    private StudentJobOpeningsRepository studentJobOpeningsRepository;

    @GetMapping("/assigned-jobs")
    public List<AssignedJobDTO> getAssignedJobsForLoggedInStudent() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String srn = authentication.getName();

        List<Object[]> rawResults = studentJobOpeningsRepository.findAssignedJobsWithStatusBySrn(srn);

        return rawResults.stream()
            .map(obj -> new AssignedJobDTO(
                (Long) obj[0],
                (String) obj[1],
                (String) obj[2],
                (String) obj[3],
                (Boolean) obj[4]
            ))
            .collect(Collectors.toList());
    }
}
