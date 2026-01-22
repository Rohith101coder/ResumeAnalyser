package com.example.resumeParser.ai;

import java.util.List;

import org.springframework.stereotype.Component;

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
      ]
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
- Focus on clarity, depth, and confidence building
""".formatted(skillsText);
}


   public  String buildDeepExplanationPrompt(List<String> skills) {

    return """
    You are a technical assistant.

    Respond ONLY in valid JSON format.
    Do NOT include markdown.
    Do NOT include explanations outside JSON.
    The response MUST be a JSON object.

    Structure:
    {
      "skills": [
        {
          "name": "skill name",
          "definition": "",
          "why_used": "",
          "real_world_usage": [],
          "learning_path": {
            "topics": [],
            "steps": []
          },
          "examples": [
            {
              "description": "",
              "code": ""
            }
          ],
          "resources": {
            "youtube": [],
            "github": [],
            "docs": []
          },
          "interview_questions": []
        }
      ]
    }

    Skills to explain:
    %s
    """.formatted(String.join(", ", skills));
}



    public  String buildQuizPrompt(List<String> skills) {

        String SkillText=String.join(", ", skills);
        return """
        You are a senior technical interviewer and subject-matter expert.

Generate MULTIPLE-CHOICE QUESTIONS (MCQs) for EACH of the following skills.

Skills:
%s

Instructions:
- Generate exactly **10 MCQs per skill**
- Questions must be **technical and concept-based**
- Difficulty level: **Intermediate to Advanced**
- Avoid overly basic or theoretical-only questions
- Questions should reflect **real-world usage, best practices, and edge cases**

For EACH question:
- Provide **4 options (A, B, C, D)**
- Only **ONE correct answer**
- Options should be realistic and non-obvious

Structure the output STRICTLY as follows:

----------------------------------------------------
Skill: <Skill Name>
----------------------------------------------------

Q1. <Question text>
A. <Option A>
B. <Option B>
C. <Option C>
D. <Option D>

Q2. ...

(repeat until Q10)

----------------------------------------------------
Correct Answers:
----------------------------------------------------
Q1. <Correct Option>
Q2. <Correct Option>
...
Q10. <Correct Option>

Guidelines:
- Do NOT include explanations unless explicitly asked
- Use correct technical terminology
- Keep formatting clean and consistent
- Ensure accuracy

Now generate the MCQs.
        """.formatted(SkillText);
    }
}
