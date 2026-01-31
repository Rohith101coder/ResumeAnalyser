package com.example.resumeParser.utility;

import org.springframework.stereotype.Component;

@Component
public class Normalization {
    public static String normalizeSkill(String skill) {
    return skill
            .toLowerCase()
            .replaceAll("\\s+", "")   // removes spaces
            .trim();
}

}
