package com.example.resumeParser.nlp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class SkillExtractor {
    
    private final Set<String> skillDictionary=new HashSet<>();

    public SkillExtractor(){
        loadSkills();
    }

    private void loadSkills(){
        try(BufferedReader reader=new BufferedReader(new InputStreamReader(new ClassPathResource("skills.txt").getInputStream()))){
            String line;
            while((line=reader.readLine())!=null){
                skillDictionary.add(line.trim().toLowerCase());
            }
        }
        catch(Exception e){
            throw new RuntimeException("Failed to load skills dictionary",e);
        }
    }

    public Set<String> extractSkills(String text){
        Set<String> extractedSkills=new HashSet<>();
        String lowerText=text.toLowerCase();

        for(String skill:skillDictionary){
            if(lowerText.contains(skill)){
                extractedSkills.add(skill);
            }
        }
        return extractedSkills;
    }
}
