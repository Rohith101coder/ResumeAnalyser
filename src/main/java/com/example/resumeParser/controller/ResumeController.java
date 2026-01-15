package com.example.resumeParser.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.resumeParser.service.ResumeService;

@RestController
@RequestMapping("/api/resume")
public class ResumeController {
    private final ResumeService resumeService;

    public ResumeController(ResumeService resumeService){
        this.resumeService=resumeService;
    }
    @PostMapping("/upload")
    public ResponseEntity<String> uploadResume(@RequestParam("file") MultipartFile file){
        String extractedText=resumeService.parseResume(file);
        return ResponseEntity.ok(extractedText);
    }

    @GetMapping("/test")
public String test() {
    return "Resume API working";
}

}
