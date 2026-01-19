package com.example.resumeParser.ai;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class PromptBuilder {

    public String buildSimpleExplanationPrompt(List<String> skills){
        return """
                Explain the following skills to a 10-year-old child.
        Use simple stories and real-life examples.
        Avoid technical jargon.
        Keep it friendly and encouraging.

        Skills: %s
                """.formatted(String.join(", ", skills));
    }
}
