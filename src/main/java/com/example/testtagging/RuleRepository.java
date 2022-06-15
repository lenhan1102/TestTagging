package com.example.testtagging;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.testtagging.entity.Rule;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

@Repository
public interface RuleRepository extends CrudRepository<Rule, Integer> {
	
}