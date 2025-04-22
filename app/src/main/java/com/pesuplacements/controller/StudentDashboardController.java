package com.pesuplacements.controller;

import com.pesuplacements.model.Student;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentDashboardController {

    @GetMapping("/studentdashboard")
    public String showStudentDashboard(Model model) {
        System.out.println(">> /studentdashboard endpoint reached - Rendering student dashboard page");

        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String srn = authentication.getName(); 
        
        Student student = Student.getInstance();
        student.setSrn(srn);
        
        model.addAttribute("student", student);

        return "studentdashboard";
    }
}
