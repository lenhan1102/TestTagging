package com.example.testtagging;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.testtagging.entity.Rule;

@Service
public class RuleService {
	private final RuleRepository ruleRepository;
	@Autowired
	public RuleService(RuleRepository ruleRepository) {
		this.ruleRepository = ruleRepository;
	}

	public void addRule(String dimension, String value, Integer target, String input, Integer rule_size) {
		
	}

	public List<Rule> findAll() {
		return (List<Rule>)ruleRepository.findAll();
	}

	public List<Rule> findByTarget(int hotelId) {
		return ruleRepository.findByTarget(hotelId);
	}
}
