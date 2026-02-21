package com.aiinternship.platform.service;

import com.aiinternship.platform.model.Resume;
import com.aiinternship.platform.repository.ResumeRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ResumeService {

    @Autowired
    private ResumeRepository resumeRepository;

    public Resume uploadResume(MultipartFile file, String email, Double cgpa) {

        try (PDDocument document = PDDocument.load(file.getInputStream())) {

            PDFTextStripper stripper = new PDFTextStripper();
            String extractedText = stripper.getText(document);

            Resume resume = new Resume();
            resume.setStudentEmail(email);
            resume.setFileName(file.getOriginalFilename());
            resume.setFileType(file.getContentType());
            resume.setResumeText(extractedText); // 🔥 important
            resume.setCgpa(cgpa);

            return resumeRepository.save(resume);

        } catch (Exception e) {
            throw new RuntimeException("Failed to read resume PDF", e);
        }
    }
}