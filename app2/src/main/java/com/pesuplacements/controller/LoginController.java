package com.pesuplacements.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    
    @GetMapping("/companylogin")
    public String showCompanyLogin() {
        System.out.println(">> /companylogin endpoint reached - Rendering company login page");
        return "companylogin"; 
    }

}
