package com.example.resumeParser.service;


import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.resumeParser.analyzer.SkillGapAnalyzer;
import com.example.resumeParser.dto.SkillGapResponse;
import com.example.resumeParser.enums.MatchPossibility;
import com.example.resumeParser.nlp.SkillExtractor;

@Service
public class SkillGapService {
    
    private final SkillExtractor skillExtractor;
    private final SkillGapAnalyzer skillGapAnalyzer;
    private static final int MAX_ALLOWED_MISSING_SKILLS = 3;
    


    public SkillGapService(SkillExtractor skillExtractor,SkillGapAnalyzer skillGapAnalyzer){
        this.skillExtractor=skillExtractor;
        this.skillGapAnalyzer=skillGapAnalyzer;
    }

    public SkillGapResponse analyze(String resumeText, String jdText){
        Set<String> resumeSkills=skillExtractor.extractSkills(resumeText);
        Set<String> jdSkills=skillExtractor.extractSkills(jdText);
        Set<String> missingSkills=skillGapAnalyzer.findMissingSkills(resumeSkills, jdSkills);
        MatchPossibility possibility=missingSkills.size()<=MAX_ALLOWED_MISSING_SKILLS?MatchPossibility.POSSIBLE:MatchPossibility.NOT_POSSIBLE;
       
        return new SkillGapResponse(resumeSkills, jdSkills, missingSkills,possibility);
    }
}

