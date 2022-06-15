package com.example.testtagging;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class TaggingResult {
    private int roomId;
    private List<Integer> ruleIds;

    public TaggingResult(int roomId) {
        this.roomId = roomId;
    }
    
    public void setRuleIds(List<Integer> ruleIds) {
    	this.ruleIds = ruleIds;
    }
}