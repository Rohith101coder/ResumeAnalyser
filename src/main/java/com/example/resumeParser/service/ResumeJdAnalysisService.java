package com.example.resumeParser.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.resumeParser.dto.SkillGapResponse;
import com.example.resumeParser.parser.ResumeParser;

@Service
public class ResumeJdAnalysisService {
    
    private final ResumeParser resumeParser;
    private final SkillGapService skillGapService;

    public ResumeJdAnalysisService(ResumeParser resumeParser,SkillGapService skillGapService){
        this.resumeParser=resumeParser;
        this.skillGapService=skillGapService;
    }

    public SkillGapResponse analyze(MultipartFile resumeFile,String jdText){
        String resumeText=resumeParser.extractText(resumeFile);
        return skillGapService.analyze(resumeText, jdText);
    }
}


//current we are using this api for resume and jd service