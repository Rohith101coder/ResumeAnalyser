package com.example.resumeParser.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.resumeParser.parser.ResumeParser;

@Service
public class ResumeService {
    private final ResumeParser resumeParser;

    public ResumeService(ResumeParser resumeParser){
        this.resumeParser=resumeParser;
    }

    public String parseResume(MultipartFile file){
        if(file.isEmpty()){
            throw new IllegalArgumentException("Resume file is empty");
        }
        return resumeParser.extractText(file);
    }
}
