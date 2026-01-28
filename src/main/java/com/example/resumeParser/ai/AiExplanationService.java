package com.example.resumeParser.ai;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.resumeParser.dto.LearningContextDTO;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class AiExplanationService {

    private final AiClient aiClient;
    private final PromptBuilder promptBuilder;
    private final LearningContextStore learningContextStore;
   

    public AiExplanationService(AiClient aiClient, PromptBuilder promptBuilder,LearningContextStore learningContextStore
           ) {
        this.aiClient = aiClient;
        this.promptBuilder = promptBuilder;
        this.learningContextStore = learningContextStore;
        
    }

    public Map<String, Object> explainSimply(List<String> missingSkills) {

        String prompt = promptBuilder.buildSimpleExplanationPrompt(missingSkills);
        String aiResponse = aiClient.generate(prompt);
        ObjectMapper objectMapper=new ObjectMapper();
        try {
            Map<String, Object> response =
                    objectMapper.readValue(aiResponse, Map.class);

            // 🔹 Extract skills
            List<Map<String, Object>> skills =
                    (List<Map<String, Object>>) response.get("skills");

            if (skills != null) {
                for (Map<String, Object> skill : skills) {

                    String skillName = skill.get("name").toString();

                    Map<String, Object> learningContextMap =
                            (Map<String, Object>) skill.get("learningContext");

                    if (learningContextMap != null) {
                        // 🔹 Convert Map → DTO
                        LearningContextDTO learningContext =
                                objectMapper.convertValue(
                                        learningContextMap,
                                        LearningContextDTO.class
                                );

                        learningContextStore.save(skillName, learningContext);
                    }
                }
            }

            // 🔹 Return full JSON to frontend
            return response;

        } catch (Exception e) {
            throw new RuntimeException("AI returned invalid JSON", e);
        }
    }

   public Map<String, Object> explainDeep(List<String> skills) {

    List<Map<String, Object>> responses = new ArrayList<>();

    for (String skill : skills) {

        LearningContextDTO context = learningContextStore.get(skill);

        if (context == null) {
            throw new RuntimeException(
                "Learning context not found for skill: " + skill
            );
        }

        String prompt = promptBuilder.buildDeepExplanationPrompt(skill, context);
        String aiResponse = aiClient.generate(prompt);

        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> parsed = mapper.readValue(aiResponse, Map.class);
            responses.add(parsed);
        } catch (Exception e) {
            throw new RuntimeException("AI returned invalid JSON for skill: " + skill, e);
        }
    }

    return Map.of("skills", responses);
}



    public Map<String,Object> getQuestions(List<String> missingSkills){
        String prompt=promptBuilder.buildQuizPrompt(missingSkills);
        String aiResponse= aiClient.generate(prompt);
        try{
            ObjectMapper mapper=new ObjectMapper();
            return mapper.readValue(aiResponse, Map.class);
        }catch(Exception e){
            throw new RuntimeException("Ai returned invalid JSON");
        }
    }
}
