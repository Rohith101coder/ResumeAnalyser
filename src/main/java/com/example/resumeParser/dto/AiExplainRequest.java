package com.example.resumeParser.dto;

import java.util.List;

public class AiExplainRequest {

    private List<String> missingSkills;

    public List<String> getMissingSkills() {
        return missingSkills;
    }

    public void setMissingSkills(List<String> missingSkills) {
        this.missingSkills = missingSkills;
    }
}
