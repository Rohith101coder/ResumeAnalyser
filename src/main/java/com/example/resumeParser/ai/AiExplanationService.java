package com.example.resumeParser.ai;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;


import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class AiExplanationService {

    private final AiClient aiClient;
    private final PromptBuilder promptBuilder;
   

    public AiExplanationService(AiClient aiClient, PromptBuilder promptBuilder
) {
        this.aiClient = aiClient;
        this.promptBuilder = promptBuilder;
       
        
    }

    public Map<String, Object> explainSimply(List<String> missingSkills) {

        String prompt = promptBuilder.buildSimpleExplanationPrompt(missingSkills);
        String aiResponse = aiClient.generate(prompt);
        ObjectMapper objectMapper=new ObjectMapper();
        try {
            Map<String, Object> response =objectMapper.readValue(aiResponse, Map.class);
            return response;
        } 
        catch (Exception e) {
            throw new RuntimeException("AI returned invalid JSON", e);
        }
    }

   public Map<String, Object> explainDeep(List<String> skills) {

        String prompt = promptBuilder.buildDeepExplanationPrompt(skills);
        String aiResponse = aiClient.generate(prompt);
        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> response = mapper.readValue(aiResponse, Map.class);
            return response;
        } 
        catch (Exception e) {
            throw new RuntimeException("AI returned invalid JSON for skill: " + e);
        }
    }

   
    public Map<String,Object> getQuestions(List<String> missingSkills){
        String prompt=promptBuilder.buildQuizPrompt(missingSkills);
        String aiResponse= aiClient.generate(prompt);
        try{
            ObjectMapper mapper=new ObjectMapper();
            Map<String,Object> response=mapper.readValue(aiResponse, Map.class);
            return response;
        }catch(Exception e){
            throw new RuntimeException("Ai returned invalid JSON");
        }
    }
}
