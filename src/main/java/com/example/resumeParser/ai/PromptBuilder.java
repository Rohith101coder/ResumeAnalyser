package com.example.resumeParser.ai;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class PromptBuilder {

   public String buildSimpleExplanationPrompt(List<String> missingSkills) {

    if (missingSkills == null || missingSkills.isEmpty()) {
        return """
        You are a career mentor.
        The candidate already meets all required skills.
        Give a short encouraging message in 2–3 lines.
        """;
    }

   String skillsText = String.join(", ", missingSkills);

    return """
You are a senior software mentor and technical storyteller.

Explain the following missing technical skills:
%s

For EACH skill:
1. Start with a short story showing real-world usage.
2. Explain the skill in very simple terms.
3. Explain why this skill is important in real jobs.
4. Give a practical real-world example.
5. Explain how a beginner can start learning it step by step.

Rules:
- Explain ONLY the skills provided above.
- Do NOT introduce unrelated skills.
- Use a friendly, motivating tone.
- Total length: 50–70 lines.

After all explanations, add a section titled "Key Takeaways" with bullet points.
""".formatted(skillsText);
}

   public static String buildDeepExplanationPrompt(List<String> skills) {

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
