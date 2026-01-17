package com.example.resumeParser.dto;

import java.util.Set;

public class SkillGapResponse {
    private Set<String> resumeSkills;
    private Set<String> jdSkills;
    private Set<String> missingSkills;

    public SkillGapResponse(Set<String> resumeSkills,Set<String> jdSkills,Set<String> missingSkills){
        this.resumeSkills=resumeSkills;
        this.jdSkills=jdSkills;
        this.missingSkills=missingSkills;
    }

    public Set<String> getResumeSkills() {
        return resumeSkills;
    }

    public Set<String> getJdSkills() {
        return jdSkills;
    }

    public Set<String> getMissingSkills() {
        return missingSkills;
    }

    
}
