package com.aiinternship.platform.controller;

import com.aiinternship.platform.model.Company;
import com.aiinternship.platform.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/company")
@CrossOrigin("*")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping("/signup")
    public String signup(@RequestBody Company company) {
        return companyService.signup(company);
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password) {
        return companyService.login(email, password);
    }
}