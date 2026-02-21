package com.aiinternship.platform.model;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Basic Info
    private String fullName;

    @Column(unique = true)
    private String email;

    private String password;

    // Survey Info
    private String interestedDomains;
    private String internshipType;
    private String expectedStipend;
    private boolean surveyCompleted = false;

    // Profile Info
    private String collegeName;
    private String degree;
    private String department;
    private String yearOfStudy;
    private Double cgpa;
    private String skills;
    private String preferredLocation;
    private String resumeFileName;
    private boolean profileCompleted = false;

    // ===== Getters & Setters =====

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getInterestedDomains() { return interestedDomains; }
    public void setInterestedDomains(String interestedDomains) { this.interestedDomains = interestedDomains; }

    public String getInternshipType() { return internshipType; }
    public void setInternshipType(String internshipType) { this.internshipType = internshipType; }

    public String getExpectedStipend() { return expectedStipend; }
    public void setExpectedStipend(String expectedStipend) { this.expectedStipend = expectedStipend; }

    public boolean isSurveyCompleted() { return surveyCompleted; }
    public void setSurveyCompleted(boolean surveyCompleted) { this.surveyCompleted = surveyCompleted; }

    public String getCollegeName() { return collegeName; }
    public void setCollegeName(String collegeName) { this.collegeName = collegeName; }

    public String getDegree() { return degree; }
    public void setDegree(String degree) { this.degree = degree; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getYearOfStudy() { return yearOfStudy; }
    public void setYearOfStudy(String yearOfStudy) { this.yearOfStudy = yearOfStudy; }

    public Double getCgpa() { return cgpa; }
    public void setCgpa(Double cgpa) { this.cgpa = cgpa; }

    public String getSkills() { return skills; }
    public void setSkills(String skills) { this.skills = skills; }

    public String getPreferredLocation() { return preferredLocation; }
    public void setPreferredLocation(String preferredLocation) { this.preferredLocation = preferredLocation; }

    public String getResumeFileName() { return resumeFileName; }
    public void setResumeFileName(String resumeFileName) { this.resumeFileName = resumeFileName; }

    public boolean isProfileCompleted() { return profileCompleted; }
    public void setProfileCompleted(boolean profileCompleted) { this.profileCompleted = profileCompleted; }
}
