package com.example.resumeParser.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.resumeParser.dto.SkillGapResponse;
import com.example.resumeParser.service.ResumeJdAnalysisService;

@RestController
@RequestMapping("/api/analyze")
public class ResumeJdAnalsisController {
    private final ResumeJdAnalysisService service;

    public ResumeJdAnalsisController( ResumeJdAnalysisService service){
        this.service=service;
    }

    @PostMapping("/resume-jd")
    public ResponseEntity<SkillGapResponse> analyzeResumeAndJd(
        @RequestParam("file") MultipartFile file,
        @RequestParam("jobDescription") String jobDescription
    ){
        SkillGapResponse response=service.analyze(file,jobDescription);
        

        return ResponseEntity.ok(response);
    }
}


// current we are using this api
//direct  resume and jd should be given together