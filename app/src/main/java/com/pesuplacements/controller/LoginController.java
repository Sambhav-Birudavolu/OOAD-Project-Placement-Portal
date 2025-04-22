package com.pesuplacements.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    
    @GetMapping("/studentlogin")
    public String showStudentLogin() {
        System.out.println(">> /studentlogin endpoint reached - Rendering student login page");
        return "studentlogin"; 
    }
    @GetMapping("/adminlogin")
    public String showAdminLoginPage() {
        return "adminlogin"; 
    }


}
