# SkillSync AI - AI-Powered Resume & Skill Gap Analyzer

![SkillSync AI Demo](Video%20Project%207.gif)

**SkillSync AI** is a full-stack, AI-driven platform designed to ingest candidate resumes, extract their technical proficiencies, compare them against a specific Job Description (JD), and offer personalized AI mentorship for the missing skills. 

Whether you're preparing for an upcoming interview or structuring a learning path, SkillSync AI acts as your personal career mentor—explaining concepts, diving deep into architectures, and testing your knowledge.

---

## 🌟 Key Features

- **Automated Resume Parsing:** Instantly extracts raw text from uploaded resumes (PDF & DOCX) using powerful document processing via **Apache Tika** & **Apache PDFBox**.
- **Intelligent Skill Gap Analysis:** Matches your existing tech stack against a predefined dictionary of industry requirements from any Job Description. It precisely isolates your "Missing Skills".
- **AI Mentorship (Three Tiers):**
  - 📖 **Story Mode:** Generates simple, beginner-friendly explanations with practical analogies.
  - 🔍 **Deep Dive:** Provides seniors with precise documentation on architectural importance and real-world system usages.
  - 🧠 **Mock Test:** Immediately builds an interactive 10-question multiple-choice quiz directly within the UI to test your knowledge of the missing skill.
- **Glassmorphism UI:** Built completely from scratch with pure modern CSS, integrating animations, dynamic interactions, and sleek layout designs without heavily relying on CSS frameworks.

---

## 🛠️ Technology Stack

### Backend
- **Framework:** Java 21, Spring Boot (Web, WebFlux for API Client, JPA Database Management).
- **Document Processing:** Apache Tika `2.9.0`, Apache PDFBox `2.0.30`.
- **AI Integration:** OpenRouter LLM API (Defaulting to `openai/gpt-4o-mini`), parsed using Jackson ObjectMappers with robust markdown-sanitized payload handlers.
- **Database:** MySQL via `mysql-connector-j`.

### Frontend
- **Framework:** React + Vite.
- **Styling:** Custom Vanilla CSS (Modern aesthetic using CSS Grid/Flexbox, dynamic variables, and Glassmorphism effects).
- **Icons:** `lucide-react`.

---

## 🚀 Getting Started

### Prerequisites
Before you begin, ensure you have the following installed on your machine:
- **Java 21 Development Kit (JDK)**
- **Node.js** (v16+) & **npm**
- **Maven**
- **MySQL Server** (running on port `3306`)
- An active **OpenRouter API Key** (Alternatively, OpenAI, Gemini).

### 1. Database Configuration
Enable your local MySQL instance and ensure you have a database created explicitly for this project:
```sql
CREATE DATABASE resumedb;
```
Ensure your `src/main/resources/application.properties` credentials map beautifully to your environment (by default: user `root`, pass ``).

### 2. Configure Required Environment Variables
To unlock the core AI-Mentorship logic, supply the application with your OpenRouter API Key. You can either hardcode the placeholder inside `application.properties` temporarily, or export it into your system environment before running the server:
```properties
# Inside src/main/resources/application.properties
OPENROUTER_API_KEY=your_actual_key_here
```

### 3. Running the Backend Server
Navigate to the root directory and start the Spring Boot application:
```bash
mvn clean compile
mvn spring-boot:run
```
*(The backend will securely mount on `http://localhost:8080`)*

### 4. Running the Frontend Dashboard
Open a new terminal session, access the `frontend` folder, install the necessary dependencies, and spin up the Vite development server:
```bash
cd frontend
npm install
npm run dev
```
*(The React frontend will spin up locally, usually accessible at `http://localhost:5173`)*

---

## 🔌 API Endpoints Reference

If you prefer interacting with the API via cURL or Postman instead of the Web UI, here are the main routes:

| HTTP Method | Endpoint | Description |
|---|---|---|
| `POST` | `/api/analyze/resume-jd` | Takes a `MultipartFile` and a `JobDescription` string, returning a `SkillGapResponse` dictating missing properties. |
| `POST` | `/api/ai/explain/simple` | Send `{"missingSkills": ["React"]}` to fetch the beginner "Story Mode" explainer. |
| `POST` | `/api/ai/explain/deep` | Send `{"missingSkills": ["React"]}` to get architectural / expert deep-dive documentation. |
| `POST` | `/api/ai/explain/questions` | Send `{"missingSkills": ["React"]}` to generate a random comprehensive list of 10 Multiple-Choice Questions (MCQ). |

---

## 🤝 Contribution and Improvements

The core API engine is designed to be highly plug'n'play. Potential scopes for evolution:
1. **Dynamic Skill Extraction:** Enhance the `nlp` package to leverage NLP Entity Recognizers (like spaCy or Stanford NLP) instead of deterministic String substring searches from `skills.txt`.
2. **Global Exception Handlers:** Introduce Spring `@ControllerAdvice` to stream graceful REST-based HTTP Status codes to the client if Tika fails mid-parse. 
3. **Database Persistency:** Actually save the generated resumes/gap-tables down efficiently into the `resumedb` for history tracking.
