package com.aiinternship.platform.repository;

import com.aiinternship.platform.model.Internship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface InternshipRepository extends JpaRepository<Internship, Long> {

    List<Internship> findByCompanyEmail(String companyEmail);

    // ✅ DELETE EXPIRED INTERNSHIPS
    void deleteByApplicationDeadlineBefore(LocalDate date);
}