package com.pesuplacements.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.pesuplacements.entity.ApplicationId;
import com.pesuplacements.entity.WithdrawalRequest;
import com.pesuplacements.entity.WithdrawalRequestId;
import com.pesuplacements.entity.StudentJobOpeningsId;
import com.pesuplacements.repository.WithdrawalRequestRepository;
import com.pesuplacements.repository.ApplicationRepository;
import com.pesuplacements.repository.StudentJobOpeningsRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;


import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class AdminDashboardController {
    
    @GetMapping("/admindashboard")
    public String showAdminDashboard() {
        System.out.println(">> /admindashboard endpoint reached - Rendering admin dashboard page");
        return "admindashboard";  
    }
}

