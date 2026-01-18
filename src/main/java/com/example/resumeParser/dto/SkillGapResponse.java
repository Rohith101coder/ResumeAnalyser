package com.example.resumeParser.dto;

import java.util.Set;

import com.example.resumeParser.enums.MatchPossibility;

public class SkillGapResponse {
    private Set<String> resumeSkills;
    private Set<String> jdSkills;
    private Set<String> missingSkills;
    private MatchPossibility matchStatus;

    

    public SkillGapResponse(Set<String> resumeSkills,Set<String> jdSkills,Set<String> missingSkills,MatchPossibility matchStatus){
        this.resumeSkills=resumeSkills;
        this.jdSkills=jdSkills;
        this.missingSkills=missingSkills;
        this.matchStatus=matchStatus;
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



    public MatchPossibility getMatchStatus() {
        return matchStatus;
    }

    
}
