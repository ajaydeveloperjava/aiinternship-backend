package com.aiinternship.platform.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "internships")
public class Internship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;

    // ✅ Company Email
    private String companyEmail;

    private String role;
    private String location;
    private String duration;
    private String roleType;
    private String internshipMode;
    private Double cgpa;

    private Boolean stipendProvided;
    private String stipend;
    private String applyLink;

    // ✅ NEW FIELD (APPLICATION DEADLINE)
    private LocalDate applicationDeadline;

    @Column(length = 1000)
    private String requiredSkills;

    @Column(length = 2000)
    private String internshipDescription;

    private Integer totalRounds;

    @Column(length = 2000)
    private String selectionProcess;

    // ===== Getters & Setters =====

    public Long getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public String getRole() {
        return role;
    }

    public String getLocation() {
        return location;
    }

    public String getDuration() {
        return duration;
    }

    public String getRoleType() {
        return roleType;
    }

    public String getInternshipMode() {
        return internshipMode;
    }

    public Double getCgpa() {
        return cgpa;
    }

    public Boolean getStipendProvided() {
        return stipendProvided;
    }

    public String getStipend() {
        return stipend;
    }

    public String getApplyLink() {
        return applyLink;
    }

    public String getRequiredSkills() {
        return requiredSkills;
    }

    public String getInternshipDescription() {
        return internshipDescription;
    }

    public Integer getTotalRounds() {
        return totalRounds;
    }

    public String getSelectionProcess() {
        return selectionProcess;
    }

    public LocalDate getApplicationDeadline() {
        return applicationDeadline;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public void setInternshipMode(String internshipMode) {
        this.internshipMode = internshipMode;
    }

    public void setCgpa(Double cgpa) {
        this.cgpa = cgpa;
    }

    public void setStipendProvided(Boolean stipendProvided) {
        this.stipendProvided = stipendProvided;
    }

    public void setStipend(String stipend) {
        this.stipend = stipend;
    }

    public void setApplyLink(String applyLink) {
        this.applyLink = applyLink;
    }

    public void setRequiredSkills(String requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public void setInternshipDescription(String internshipDescription) {
        this.internshipDescription = internshipDescription;
    }

    public void setTotalRounds(Integer totalRounds) {
        this.totalRounds = totalRounds;
    }

    public void setSelectionProcess(String selectionProcess) {
        this.selectionProcess = selectionProcess;
    }

    public void setApplicationDeadline(LocalDate applicationDeadline) {
        this.applicationDeadline = applicationDeadline;
    }
}