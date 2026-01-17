package com.example.resumeParser.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.resumeParser.dto.SkillGapRequest;
import com.example.resumeParser.dto.SkillGapResponse;
import com.example.resumeParser.service.SkillGapService;

@RestController
@RequestMapping("/api/analyze")
public class SkillGapController {
    private final SkillGapService skillGapService;

    public SkillGapController(SkillGapService skillGapService){
        this.skillGapService=skillGapService;
    }

    @PostMapping("/skills")
    public ResponseEntity<SkillGapResponse> analyzeSkills(@RequestBody SkillGapRequest request){
        SkillGapResponse response=skillGapService.analyze(
            request.getResumeText(),
            request.getJobDescription()
        );

        return ResponseEntity.ok(response);
    }
}
