package com.example.resumeParser.controller;



import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.example.resumeParser.ai.AiExplanationService;
import com.example.resumeParser.dto.AiExplainRequest;

import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/ai/explain")
@CrossOrigin(origins = "*")
public class AiExplanationController {

    private final AiExplanationService service;

    public AiExplanationController(AiExplanationService service){
            this.service=service;
    }

   @PostMapping("/simple")
public ResponseEntity<Map<String,Object>> explainSimple(
        @RequestBody AiExplainRequest request) {
                 System.out.println("Received skills: " + request.getMissingSkills());

    return ResponseEntity.ok(
            service.explainSimply(request.getMissingSkills())
    );
}

// @PostMapping("/deep")
// public ResponseEntity<String> explainDeep(
//         @RequestBody AiExplainRequest request) {
//                  System.out.println("Received skills: " + request.getMissingSkills());

//     return ResponseEntity.ok(
//             service.explainDeep(request.getMissingSkills())
//     );
// }
@PostMapping("/deep")
public ResponseEntity<Map<String, Object>> explainDeep(
        @RequestBody AiExplainRequest request) {

    return ResponseEntity.ok(
        service.explainDeep(request.getMissingSkills())
    );
}


@PostMapping("/questions")
public ResponseEntity<Map<String,Object>> getQuestions(
        @RequestBody AiExplainRequest request) {
                 System.out.println("Received skills: " + request.getMissingSkills());
    return ResponseEntity.ok(
            service.getQuestions(request.getMissingSkills())
    );
}



}
