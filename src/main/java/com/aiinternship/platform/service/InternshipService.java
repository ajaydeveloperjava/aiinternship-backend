package com.aiinternship.platform.service;

import com.aiinternship.platform.model.Internship;
import com.aiinternship.platform.repository.InternshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class InternshipService {

    @Autowired
    private InternshipRepository internshipRepository;

    // POST INTERNSHIP
    public String postInternship(Internship internship) {
        internshipRepository.save(internship);
        return "Internship Posted Successfully";
    }

    // COMPANY-WISE INTERNSHIPS
    public List<Internship> getInternshipsByCompany(String companyEmail) {
        return internshipRepository.findByCompanyEmail(companyEmail);
    }

    public List<Internship> getAllInternships() {
        return internshipRepository.findAll();
    }

    // GET INTERNSHIP BY ID
    public Internship getInternshipById(Long id) {
        return internshipRepository.findById(id).orElse(null);
    }

    // UPDATE INTERNSHIP
    public String updateInternship(Long id, Internship updated) {

        Internship existing = internshipRepository.findById(id).orElse(null);

        if (existing == null) {
            return "Internship Not Found";
        }

        existing.setCompanyName(updated.getCompanyName());
        existing.setRole(updated.getRole());
        existing.setLocation(updated.getLocation());
        existing.setDuration(updated.getDuration());
        existing.setRoleType(updated.getRoleType());
        existing.setInternshipMode(updated.getInternshipMode());
        existing.setCgpa(updated.getCgpa());
        existing.setStipendProvided(updated.getStipendProvided());
        existing.setStipend(updated.getStipend());
        existing.setApplyLink(updated.getApplyLink());
        existing.setRequiredSkills(updated.getRequiredSkills());
        existing.setInternshipDescription(updated.getInternshipDescription());
        existing.setTotalRounds(updated.getTotalRounds());
        existing.setSelectionProcess(updated.getSelectionProcess());
        existing.setApplicationDeadline(updated.getApplicationDeadline());

        internshipRepository.save(existing);
        return "Internship Updated Successfully";
    }

    // DELETE INTERNSHIP
    public String deleteInternship(Long id) {
        internshipRepository.deleteById(id);
        return "Internship Deleted Successfully";
    }

    // ✅ AUTO DELETE AFTER DEADLINE (RUNS DAILY AT MIDNIGHT)
    @Scheduled(cron = "0 0 0 * * ?")
    public void deleteExpiredInternships() {
        internshipRepository.deleteByApplicationDeadlineBefore(LocalDate.now());
    }
}