package com.aiinternship.platform.service;

public class ResumeTextNormalizer {

    public static String normalize(String text) {

        if (text == null) return "";

        text = text.toLowerCase();

        // Fix broken characters from PDF (s p r i n g → spring)
        text = text.replaceAll("([a-z])\\s+([a-z])", "$1$2");

        // Replace common separators with commas
        text = text.replace(":", ",");
        text = text.replace("/", " ");

        // Remove brackets content
        text = text.replaceAll("\\(.*?\\)", "");

        // Remove junk characters
        text = text.replaceAll("[^a-z0-9, ]", " ");

        // Normalize spaces
        text = text.replaceAll("\\s+", " ");

        return text.trim();
    }
}