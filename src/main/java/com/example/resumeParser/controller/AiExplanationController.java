package com.example.resumeParser.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.example.resumeParser.ai.AiExplanationService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("api/ai/explain")
public class AiExplanationController {

    private final AiExplanationService service;

    public AiExplanationController(AiExplanationService service){
            this.service=service;
    }


    public ResponseEntity<String> explainSimple(@RequestBody List<String> missingSkills){
        return ResponseEntity.ok(service.explainSimply(missingSkills));
        
    }
}
