package com.example.resumeParser.dto;

import java.util.List;

public class LearningContextDTO {
    private List<String> coreKeywords;
    private String mentalModel;
    private List<String> keyConceptsIntroduced;
    private String assumedUnderstandingAfterThisExplanation;
    public List<String> getCoreKeywords() {
        return coreKeywords;
    }
    public void setCoreKeywords(List<String> coreKeywords) {
        this.coreKeywords = coreKeywords;
    }
    public String getMentalModel() {
        return mentalModel;
    }
    public void setMentalModel(String mentalModel) {
        this.mentalModel = mentalModel;
    }
    public List<String> getKeyConceptsIntroduced() {
        return keyConceptsIntroduced;
    }
    public void setKeyConceptsIntroduced(List<String> keyConceptsIntroduced) {
        this.keyConceptsIntroduced = keyConceptsIntroduced;
    }
    public String getAssumedUnderstandingAfterThisExplanation() {
        return assumedUnderstandingAfterThisExplanation;
    }
    public void setAssumedUnderstandingAfterThisExplanation(String assumedUnderstandingAfterThisExplanation) {
        this.assumedUnderstandingAfterThisExplanation = assumedUnderstandingAfterThisExplanation;
    }

    
}
