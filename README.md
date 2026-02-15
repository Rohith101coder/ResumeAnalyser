Resume Skill Gap Analyzer with AI

An AI-powered backend application that analyzes missing skills and provides structured explanations, deep technical insights, quizzes, and learning guidance using modern LLM APIs.
Built with Spring Boot, designed for clean JSON responses and frontend-friendly integration.

🚀 Project Overview

This project helps users understand skill gaps by:

Explaining missing skills in a simple and confidence-building way

Providing deep technical explanations when the user wants to go deeper

Generating MCQs (tests) to evaluate understanding

Returning well-structured JSON, making frontend styling easy

The system is designed like a career mentor + technical guide rather than just a chatbot.

🎯 Problem It Solves

Users often don’t know what a skill really means beyond buzzwords

Generic AI responses are hard to format and reuse on the frontend

Learning paths are unclear and not personalized

This project solves that by:

Breaking explanations into clear sections

Separating simple learning from deep technical learning

Returning consistent JSON responses for UI rendering

Preparing users for real-world usage and interviews

🧠 Key Features
1️⃣ Simple Skill Explanation (Beginner-Friendly)

Explains what the skill is, why it matters, and where it’s used

Uses clear language assuming basic programming knowledge

Includes a learning context (keywords, mental model, concepts)

2️⃣ Deep Technical Explanation

Builds on the simple explanation

Focuses on architecture, internals, performance, and real-world systems

Includes:

Learning path

Production-style examples

Resources (Docs, GitHub, YouTube)

Interview questions

3️⃣ Skill-Based MCQ Generation

Generates 10 technical MCQs per skill

Intermediate to advanced difficulty

Suitable for self-assessment and interviews

4️⃣ Clean JSON Responses

No raw text blobs

Easy to render on frontend (cards, tabs, PDFs, quizzes)

Designed for scalability and UI styling

🛠️ Tech Stack

Java 17+

Spring Boot

Spring Web

WebClient

Jackson ObjectMapper

OpenRouter API (LLM Integration)

Postman (API Testing)

📡 API Endpoints
🔹 Simple Explanation
POST /api/ai/explain/simple


Request

{
  "missingSkills": ["Docker", "Kubernetes"]
}


Response

Beginner-friendly explanation

Learning context included

JSON structured output

🔹 Deep Explanation
POST /api/ai/explain/deep


Response

Technical depth

Real-world usage

Code examples

Resources & interview questions

🔹 Skill Quiz
POST /api/ai/explain/questions


Response

10 MCQs per skill

Correct answers included

Clean structured format

🧩 Project Architecture
controller
 └── AiExplanationController

service
 └── AiExplanationService

ai
 ├── AiClient
 ├── OpenRouterAiClient
 └── PromptBuilder

dto
 └── AiExplainRequest

🧪 Testing

Fully tested using Postman

Verified:

Simple explanation flow

Deep explanation flow

Quiz generation

JSON validated using ObjectMapper

📈 Impact & Metrics

Reduced unstructured AI output by 100%

Improved frontend rendering flexibility

Faster learning comprehension with layered explanations

Designed to scale for:

Resume analyzers

Learning platforms

Career guidance systems

🔮 Future Enhancements

PDF generation for explanations

Database persistence for learning history

Authentication & user profiles

Frontend UI (React / Angular)

Skill progress tracking

👨‍💻 Author

Rohith
Backend Developer | Java | Spring Boot | AI Integration
