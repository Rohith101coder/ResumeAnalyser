package com.example.resumeParser.parser;

import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ResumeParser {
    private final Tika tike=new Tika();


    public String extractText(MultipartFile file){
        try{
            return tike.parseToString(file.getInputStream());
        }catch(Exception e){
            throw new RuntimeException("Fail to parse resumr",e);
        }
    }
}
