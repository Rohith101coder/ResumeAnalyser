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

    public  String buildDeepExplanationPrompt(List<String> missingSkills) {
         String skillsText = String.join(", ", missingSkills);
        return """
       You are a senior software engineer and technical architect.

Explain EACH of the following skills in a highly technical, structured, and professional manner suitable for:
- software engineers
- computer science students
- technical interviews
- real-world project implementation

Skills to explain:
%s

For EACH skill, strictly follow this structure:

----------------------------------------------------
1. Technical Definition
----------------------------------------------------
- Precise and clear definition
- Explain the core purpose of the skill
- Use standard industry terminology

----------------------------------------------------
2. Why This Skill Is Used
----------------------------------------------------
- Problems it solves
- Benefits over alternatives
- Impact on scalability, performance, or productivity

----------------------------------------------------
3. Real-World Usage
----------------------------------------------------
- Enterprise and industry use cases
- How it is used in production systems
- Integration in modern software architectures

----------------------------------------------------
4. Core Concepts and Topics
----------------------------------------------------
- List all major concepts related to the skill
- Explain each concept briefly and technically

----------------------------------------------------
5. Learning Roadmap (Beginner → Advanced)
----------------------------------------------------
- Step-by-step technical learning path
- Foundational topics
- Advanced and production-level topics
- Best practices and common pitfalls

----------------------------------------------------
6. Skill-Type Based Deep Dive
----------------------------------------------------
IF the skill is a PROGRAMMING LANGUAGE:
- Language fundamentals
- Syntax and core constructs
- Data structures and control flow
- Object-Oriented / Functional concepts (if applicable)
- Standard libraries and frameworks
- Tooling and ecosystem
- Typical use cases

IF the skill is a TOOL / FRAMEWORK / PLATFORM:
- Architecture and internal components
- Key features and capabilities
- Configuration and setup
- Deployment and integration
- Security and performance considerations
- Best practices

----------------------------------------------------
7. Code Examples
----------------------------------------------------
- Provide clean, real-world code examples
- Include comments for clarity
- Explain the code line-by-line
- Keep examples realistic and production-relevant

----------------------------------------------------
8. Practical Implementation
----------------------------------------------------
- How this skill is applied in real projects
- Example project scenarios
- How teams use it in day-to-day development

----------------------------------------------------
9. Practice and Skill Validation
----------------------------------------------------
- Hands-on practice recommendations
- Mini-project ideas
- How to assess proficiency

----------------------------------------------------
10. Learning Resources
----------------------------------------------------
Provide high-quality technical resources:
- Official documentation
- YouTube technical channels
- GitHub repositories
- Blogs or free courses

----------------------------------------------------
11. Interview Preparation
----------------------------------------------------
- Common interview questions
- Scenario-based questions
- Practical coding or design questions

----------------------------------------------------
12. Summary
----------------------------------------------------
- Key takeaways
- What makes this skill valuable
- How it fits into a professional software engineer’s skill set

Guidelines:
- Be technical and precise
- Avoid storytelling or motivational language
- Use professional tone
- Cover the topic comprehensively
- Ensure clarity and depth

Now generate the explanation.
        """.formatted(skillsText);
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
