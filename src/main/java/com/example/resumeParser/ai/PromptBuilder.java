package com.example.resumeParser.ai;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.resumeParser.dto.LearningContextDTO;

@Component
public class PromptBuilder {

   public String buildSimpleExplanationPrompt(List<String> missingSkills) {

    if (missingSkills == null || missingSkills.isEmpty()) {
        return """
        Respond ONLY in valid JSON.

        {
          "status": "no_missing_skills",
          "message": "You already have all the required skills for this role. Stay consistent, keep practicing, and aim higher — you are doing great."
        }
        """;
    }

    String skillsText = String.join(", ", missingSkills);

    return """
You are a senior software engineer and career mentor.

Respond ONLY in valid JSON.
Do NOT include markdown.
Do NOT include explanations outside JSON.

Generate a detailed but easy-to-understand response using the following structure:

{
  "status": "missing_skills_found",
  "skills": [
    {
      "name": "<skill name>",

      "whatItIs": "<Explain what this skill is using very simple words, assuming the user is a beginner>",

      "whyItIsImportant": "<Explain clearly why companies use this skill and why it matters in real jobs>",

      "whereItIsUsed": "<Explain where this skill is used in real-world projects, products, or systems>",

      "howItWorksInSimpleTerms": "<Explain how it works internally in a simplified way>",

      "practicalExample": "<Give a realistic and easy example of using this skill>",

      "howToStartLearning": [
        "<Beginner step 1>",
        "<Beginner step 2>",
        "<Beginner step 3>",
        "<Beginner step 4>",
        "<Beginner step 5>"
      ],

      "learningContext": {
        "coreKeywords": [
          "<important keyword 1>",
          "<important keyword 2>",
          "<important keyword 3>"
        ],
        "mentalModel": "<One short sentence describing how the learner should imagine this skill working>",
        "keyConceptsIntroduced": [
          "<concept 1>",
          "<concept 2>",
          "<concept 3>"
        ],
        "assumedUnderstandingAfterThisExplanation": "<Describe what the learner is expected to understand after reading this explanation>"
      }
    }
  ],

  "overallGuidance": {
    "confidenceMessage": "<Motivating message that reassures the learner>",
    "learningTip": "<Advice on how to approach learning these skills step by step>"
  }
}

Skills to explain:
%s

Rules:
- Generate ONE detailed object per skill
- Explain concepts slowly and clearly
- Assume the reader has basic programming knowledge
- Do NOT introduce unrelated skills
- learningContext MUST align with the explanations above
- learningContext will be reused for deep explanations
- Focus on clarity, depth, and confidence building
""".formatted(skillsText);
}


   public String buildDeepExplanationPrompt(List<String> skill) {

      String skillsText = String.join(", ", skill);
    return """
You are a senior technical expert and system designer.

Respond ONLY in valid JSON.
Do NOT include markdown.
Do NOT include explanations outside JSON.
The response MUST be a single JSON object.

IMPORTANT CONTEXT:
The learner has already received a simple explanation.
Give explanation in deep way how does it useful


Now generate a DEEP technical explanation for the skill: 

JSON Structure:
{
  "skill": {
    "name": "<skill name>",

    "definition": "<Precise technical definition, deeper than beginner level>",

    "why_used": "<Technical and architectural reasons why this skill is used>",

    "real_world_usage": [
      "<Usage in real production systems>",
      "<Enterprise or large-scale use cases>"
    ],

    "learning_path": {
      "topics": [
        "<Core topic 1>",
        "<Core topic 2>",
        "<Advanced topic 3>"
      ],
      "steps": [
        "<Step-by-step deep learning progression>"
      ]
    },

    "examples": [
      {
        "description": "<Realistic technical scenario>",
        "code": "<Production-style example code>"
      }
    ],

    "resources": {
      "youtube": ["<High-quality tutorial link>"],
      "github": ["<Official or popular repo>"],
      "docs": ["<Official documentation link>"]
    },

    "interview_questions": [
      "<Intermediate-level question>",
      "<Advanced scenario-based question>"
    ]
  }
}

Rules:
- Go deeper, not wider
- Keep explanations structured and professional
- Assume the learner already understands the basics
""".formatted(skillsText);
}




    public String buildQuizPrompt(List<String> skills) {

    String skillsText = String.join(", ", skills);

    return """
You are a senior technical interviewer and assessment designer.

Respond ONLY in valid JSON.
Do NOT include markdown.
Do NOT include explanations outside JSON.

Generate exactly 10 MULTIPLE-CHOICE QUESTIONS (MCQs) for EACH skill.

Use the following JSON structure strictly:

{
  "status": "quiz_generated",
  "skills": [
    {
      "skillName": "<Skill Name>",
      "questions": [
        {
          "questionNumber": 1,
          "question": "<Technical, scenario-based question>",
          "options": {
            "A": "<Option A>",
            "B": "<Option B>",
            "C": "<Option C>",
            "D": "<Option D>"
          },
          "correctAnswer": "<A | B | C | D>"
        }
      ]
    }
  ]
}

Question rules:
- Generate EXACTLY 10 questions per skill
- Difficulty level: Easy to Intermediate
- Focus on real-world usage, best practices, edge cases, and common pitfalls
- include some basic definition-only questions also
- Only ONE correct option per question
- Options must be realistic and non-trivial
- Use correct technical terminology

Skills to generate questions for:
%s

Important:
- Do NOT add explanations
- Do NOT add extra text
- Do NOT add unrelated skills
- Maintain clean and consistent structure
""".formatted(skillsText);
}

}
