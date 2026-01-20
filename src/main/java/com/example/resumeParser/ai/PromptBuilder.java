package com.example.resumeParser.ai;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class PromptBuilder {

    public String buildSimpleExplanationPrompt(List<String> skills){
         if (skills == null || skills.isEmpty()) {
        return "No skills were provided to explain.";
    }

        return """
                Explain the following skills to a 10-year-old child.
        Use simple stories and real-life examples.
        Avoid technical jargon.
        Keep it friendly and encouraging.

        Skills: %s
                """.formatted(String.join(", ", skills));
    }
    public static String buildDeepExplanationPrompt(List<String> skills) {

        return """
        Explain the following skills in detail with:
        - clear definition
        - why it is used
        - where it is used in real projects
        - simple examples

        Skills:
        %s
        """.formatted(String.join(", ", skills));
    }
    public static String buildQuizPrompt(List<String> skills) {

        return """
        Generate 10 multiple-choice questions (MCQs)
        for each of the following skills.
        Provide correct answers at the end.

        Skills:
        %s
        """.formatted(String.join(", ", skills));
    }
}
