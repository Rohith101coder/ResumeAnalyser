package com.example.resumeParser.ai;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class AiExplanationService {
    private final AiClient aiClient;
    private final PromptBuilder promptBuilder;

    public AiExplanationService(AiClient aiClient,PromptBuilder promptBuilder){
        this.aiClient=aiClient;
        this.promptBuilder=promptBuilder;
    }

    public String explainSimply(List<String> missingSkills){
        String prompt=promptBuilder.buildSimpleExplanationPrompt(missingSkills);

        return aiClient.generate(prompt);
    }
}
