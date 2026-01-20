package com.example.resumeParser.controller;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.example.resumeParser.ai.AiExplanationService;
import com.example.resumeParser.dto.AiExplainRequest;

import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/ai/explain")
public class AiExplanationController {

    private final AiExplanationService service;

    public AiExplanationController(AiExplanationService service){
            this.service=service;
    }

   @PostMapping("/simple")
public ResponseEntity<String> explainSimple(
        @RequestBody AiExplainRequest request) {
                 System.out.println("Received skills: " + request.getMissingSkills());

    return ResponseEntity.ok(
            service.explainSimply(request.getMissingSkills())
    );
}

@PostMapping("/deep")
public ResponseEntity<String> explainDeep(
        @RequestBody AiExplainRequest request) {
                 System.out.println("Received skills: " + request.getMissingSkills());

    return ResponseEntity.ok(
            service.explainDeep(request.getMissingSkills())
    );
}



}
