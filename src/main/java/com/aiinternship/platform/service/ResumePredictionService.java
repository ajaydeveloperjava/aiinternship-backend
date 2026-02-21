package com.aiinternship.platform.service;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ResumePredictionService {

    /* ========= Extract ONLY Skills Section ========= */
    private String extractSkillsSection(String text) {

        if (text == null) return "";

        String lower = text.toLowerCase();

        String[] startHeaders = {
                "technical skills", "skills", "technologies", "key skills"
        };
        String[] endHeaders = {
                "experience", "projects", "education", "certification", "summary"
        };

        for (String start : startHeaders) {
            int s = lower.indexOf(start);
            if (s != -1) {
                String section = text.substring(s);
                String sectionLower = section.toLowerCase();

                for (String end : endHeaders) {
                    int e = sectionLower.indexOf(end);
                    if (e != -1) {
                        section = section.substring(0, e);
                        break;
                    }
                }
                return section;
            }
        }
        return "";
    }

    /* ========= Normalize ========= */
    private String normalize(String text) {
        if (text == null) return "";

        text = text.toLowerCase();
        text = text.replaceAll("([a-z])\\s+([a-z])", "$1$2"); // pdf breaks
        text = text.replace(":", ",");
        text = text.replace("/", " ");
        text = text.replaceAll("\\(.*?\\)", "");
        text = text.replaceAll("[^a-z0-9, ]", " ");
        text = text.replaceAll("\\s+", " ");
        return text.trim();
    }

    private List<String> extractSkills(String text) {
        text = normalize(text);
        List<String> list = new ArrayList<>();
        for (String s : text.split(",")) {
            s = s.trim();
            if (s.length() > 1) list.add(s);
        }
        return list;
    }

    private boolean isMatched(String resumeSkill, String requiredSkill) {
        Set<String> r1 = new HashSet<>(Arrays.asList(resumeSkill.split(" ")));
        Set<String> r2 = new HashSet<>(Arrays.asList(requiredSkill.split(" ")));

        int match = 0;
        for (String t : r2) if (r1.contains(t)) match++;
        return !r2.isEmpty() && ((double) match / r2.size()) >= 0.6;
    }

    /* ========= FINAL PREDICTION ========= */
    public Map<String, Object> predict(
            String resumeText,
            String internshipSkills,
            Double studentCgpa,
            Double requiredCgpa) {

        List<String> resumeSkills =
                extractSkills(extractSkillsSection(resumeText));

        List<String> requiredSkills =
                extractSkills(internshipSkills);

        List<String> matched = new ArrayList<>();
        List<String> missing = new ArrayList<>();
        Map<String, String> reasons = new HashMap<>();
        List<String> tips = new ArrayList<>();

        for (String req : requiredSkills) {
            boolean found = false;
            for (String res : resumeSkills) {
                if (isMatched(res, req)) {
                    found = true;
                    reasons.put(req, "Found in Technical Skills section");
                    break;
                }
            }
            if (found) {
                matched.add(req);
            } else {
                missing.add(req);
                reasons.put(req, "Not found in Technical Skills section");
                tips.add("Add \"" + req + "\" in Technical Skills or Projects section");
            }
        }

        if (studentCgpa != null && requiredCgpa != null && studentCgpa < requiredCgpa) {
            tips.add("Improve CGPA to " + requiredCgpa + "+ to increase score");
        }

        double skillScore = requiredSkills.isEmpty() ? 0 :
                ((double) matched.size() / requiredSkills.size()) * 70;

        double cgpaScore = (studentCgpa != null && requiredCgpa != null && requiredCgpa > 0)
                ? Math.min(30, (studentCgpa / requiredCgpa) * 30)
                : 0;

        long percentage = Math.round(Math.min(100, skillScore + cgpaScore));

        Map<String, Object> result = new HashMap<>();
        result.put("matchPercentage", percentage);
        result.put("verdict",
                percentage >= 80 ? "High chance of shortlisting" :
                        percentage >= 50 ? "Medium chance of shortlisting" :
                                "Low chance of shortlisting");

        result.put("matchedSkills", matched);
        result.put("missingSkills", missing);
        result.put("skillReasons", reasons);
        result.put("improvementTips", tips);

        return result;
    }
}