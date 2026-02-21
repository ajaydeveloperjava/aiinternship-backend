package com.aiinternship.platform.controller;

import com.aiinternship.platform.model.Internship;
import com.aiinternship.platform.service.InternshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/internships")
@CrossOrigin("*")
public class InternshipController {

    @Autowired
    private InternshipService internshipService;

    @PostMapping("/post")
    public String postInternship(@RequestBody Internship internship) {
        return internshipService.postInternship(internship);
    }

    @GetMapping("/company")
    public List<Internship> getByCompany(@RequestParam String email) {
        return internshipService.getInternshipsByCompany(email);
    }

    @GetMapping("/{id}")
    public Internship getById(@PathVariable Long id) {
        return internshipService.getInternshipById(id);
    }

    @PutMapping("/update/{id}")
    public String updateInternship(@PathVariable Long id,
                                   @RequestBody Internship internship) {
        return internshipService.updateInternship(id, internship);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteInternship(@PathVariable Long id) {
        return internshipService.deleteInternship(id);
    }

    @GetMapping("/all")
    public List<Internship> getAllInternships() {
        return internshipService.getAllInternships();
    }
}