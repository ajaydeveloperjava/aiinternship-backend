package com.aiinternship.platform.service;

import com.aiinternship.platform.model.Company;
import com.aiinternship.platform.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    // SIGNUP
    public String signup(Company company) {

        if(companyRepository.findByEmail(company.getEmail()) != null) {
            return "Email already registered";
        }

        companyRepository.save(company);
        return "Signup successful";
    }

    // LOGIN
    public String login(String email, String password) {

        Company company = companyRepository.findByEmail(email);

        if(company == null) {
            return "Company not found";
        }

        if(!company.getPassword().equals(password)) {
            return "Invalid password";
        }

        return "Login successful";
    }
}