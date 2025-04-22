package com.pesuplacements.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CompanyDashboardController {

    @GetMapping("/companydashboard")
    public String showCompanyDashboard() {
        System.out.println(">> /companydashboard endpoint reached - Rendering company dashboard page");
        return "companydashboard";
    }
}
