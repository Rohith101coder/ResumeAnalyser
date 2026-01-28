package com.example.resumeParser.ai;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.example.resumeParser.dto.LearningContextDTO;

@Component
public class LearningContextStore {
    private final Map<String, LearningContextDTO> store=new ConcurrentHashMap<>();

    public void save(String skillName, LearningContextDTO context){
        store.put(skillName.toLowerCase(),context);
    }

    public LearningContextDTO get(String skillName){
        return store.get(skillName.toLowerCase());
    }
}
