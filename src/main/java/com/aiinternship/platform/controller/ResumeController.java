package com.aiinternship.platform.controller;

import com.aiinternship.platform.model.Resume;
import com.aiinternship.platform.model.Internship;
import com.aiinternship.platform.repository.ResumeRepository;
import com.aiinternship.platform.repository.InternshipRepository;
import com.aiinternship.platform.service.ResumeService;
import com.aiinternship.platform.service.ResumePredictionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/resume")
@CrossOrigin("*")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    @Autowired
    private ResumePredictionService resumePredictionService;

    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private InternshipRepository internshipRepository;

    @PostMapping("/upload")
    public Resume uploadResume(
            @RequestParam MultipartFile file,
            @RequestParam String email,
            @RequestParam Double cgpa) {

        return resumeService.uploadResume(file, email, cgpa);
    }

    @PostMapping("/predict")
    public Map<String, Object> predict(
            @RequestParam Long resumeId,
            @RequestParam Long internshipId) {

        Resume resume = resumeRepository.findById(resumeId)
                .orElseThrow();

        Internship internship = internshipRepository.findById(internshipId)
                .orElseThrow();

        return resumePredictionService.predict(
                resume.getResumeText(),
                internship.getRequiredSkills(),
                resume.getCgpa(),
                internship.getCgpa()
        );
    }
}