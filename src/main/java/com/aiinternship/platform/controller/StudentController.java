package com.aiinternship.platform.controller;

import com.aiinternship.platform.model.Student;
import com.aiinternship.platform.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
@CrossOrigin("*")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // ================= SIGNUP =================
    @PostMapping("/signup")
    public String signup(@RequestBody Student student) {
        return studentService.register(student);
    }

    // ================= LOGIN =================
    @PostMapping("/login")
    public String login(@RequestBody Student loginRequest) {
        return studentService.login(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        );
    }

    // ================= SUBMIT SURVEY =================
    @PostMapping("/submitSurvey")
    public String submitSurvey(@RequestParam String email,
                               @RequestParam String domains,
                               @RequestParam String internshipType,
                               @RequestParam String expectedStipend) {

        return studentService.submitSurvey(
                email,
                domains,
                internshipType,
                expectedStipend
        );
    }

    // ================= CREATE PROFILE =================
    @PostMapping("/createProfile")
    public String createProfile(@RequestBody Student student) {

        return studentService.createProfile(
                student.getEmail(),
                student.getCollegeName(),
                student.getDegree(),
                student.getDepartment(),
                student.getYearOfStudy(),
                student.getCgpa(),
                student.getSkills(),
                student.getPreferredLocation(),
                student.getResumeFileName()
        );
    }

    // ================= GET PROFILE =================
    @GetMapping("/profile")
    public Student getProfile(@RequestParam String email) {
        return studentService.getProfile(email);
    }

    // ================= UPDATE PROFILE =================
    @PostMapping("/updateProfile")
    public String updateProfile(@RequestBody Student student) {

        return studentService.updateProfile(
                student.getEmail(),
                student.getCollegeName(),
                student.getDegree(),
                student.getDepartment(),
                student.getYearOfStudy(),
                student.getCgpa(),
                student.getSkills(),
                student.getPreferredLocation(),
                student.getResumeFileName()
        );
    }
}
