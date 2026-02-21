package com.aiinternship.platform.service;

import com.aiinternship.platform.model.Student;
import com.aiinternship.platform.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    private static final String PASSWORD_PATTERN =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).{8,}$";

    // ================= SIGNUP =================
    public String register(Student student) {

        if (studentRepository.existsByEmail(student.getEmail())) {
            return "Email already registered.";
        }

        if (!Pattern.matches(PASSWORD_PATTERN, student.getPassword())) {
            return "Password must contain minimum 8 characters, uppercase, lowercase, number and special character.";
        }

        studentRepository.save(student);
        return "Signup successful";
    }

    // ================= LOGIN =================
    public String login(String email, String password) {

        Optional<Student> optionalStudent = studentRepository.findByEmail(email);

        if (optionalStudent.isEmpty()) {
            return "Email not registered";
        }

        Student student = optionalStudent.get();

        if (!student.getPassword().equals(password)) {
            return "Wrong password";
        }

        if (!student.isSurveyCompleted()) {
            return "SURVEY_REQUIRED";
        }

        if (!student.isProfileCompleted()) {
            return "PROFILE_REQUIRED";
        }

        return "Login Successful";
    }

    // ================= SUBMIT SURVEY =================
    public String submitSurvey(String email,
                               String domains,
                               String internshipType,
                               String expectedStipend) {

        Optional<Student> optionalStudent = studentRepository.findByEmail(email);

        if (optionalStudent.isEmpty()) {
            return "User not found";
        }

        Student student = optionalStudent.get();

        student.setInterestedDomains(domains);
        student.setInternshipType(internshipType);
        student.setExpectedStipend(expectedStipend);
        student.setSurveyCompleted(true);

        studentRepository.save(student);

        return "Survey Completed";
    }

    // ================= CREATE PROFILE =================
    public String createProfile(String email,
                                String collegeName,
                                String degree,
                                String department,
                                String yearOfStudy,
                                Double cgpa,
                                String skills,
                                String preferredLocation,
                                String resumeFileName) {

        Optional<Student> optionalStudent = studentRepository.findByEmail(email);

        if (optionalStudent.isEmpty()) {
            return "User not found";
        }

        Student student = optionalStudent.get();

        student.setCollegeName(collegeName);
        student.setDegree(degree);
        student.setDepartment(department);
        student.setYearOfStudy(yearOfStudy);
        student.setCgpa(cgpa);
        student.setSkills(skills);
        student.setPreferredLocation(preferredLocation);
        student.setResumeFileName(resumeFileName);
        student.setProfileCompleted(true);

        studentRepository.save(student);

        return "Profile Created Successfully";
    }

    // ================= GET PROFILE =================
    public Student getProfile(String email) {
        return studentRepository.findByEmail(email).orElse(null);
    }

    // ================= UPDATE PROFILE =================
    public String updateProfile(String email,
                                String collegeName,
                                String degree,
                                String department,
                                String yearOfStudy,
                                Double cgpa,
                                String skills,
                                String preferredLocation,
                                String resumeFileName) {

        Optional<Student> optionalStudent = studentRepository.findByEmail(email);

        if (optionalStudent.isEmpty()) {
            return "User not found";
        }

        Student student = optionalStudent.get();

        student.setCollegeName(collegeName);
        student.setDegree(degree);
        student.setDepartment(department);
        student.setYearOfStudy(yearOfStudy);
        student.setCgpa(cgpa);
        student.setSkills(skills);
        student.setPreferredLocation(preferredLocation);
        student.setResumeFileName(resumeFileName);
        student.setProfileCompleted(true);

        studentRepository.save(student);

        return "Profile Updated Successfully";
    }
}
