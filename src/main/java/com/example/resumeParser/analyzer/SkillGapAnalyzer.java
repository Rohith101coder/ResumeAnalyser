package com.example.resumeParser.analyzer;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class SkillGapAnalyzer {
    
    public Set<String> findMissingSkills(Set<String> resumeSkills,Set<String> jdSkills){
        Set<String> missingSkills=new HashSet<>(jdSkills);
        missingSkills.removeAll(resumeSkills);
        return missingSkills;

    }
}


// This component compares skills extracted from a resume and a job description.
// It identifies the skill gap by finding skills required in the JD
// that are missing from the candidate's resume.
