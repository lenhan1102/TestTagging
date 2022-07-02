package com.example.testtagging;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.testtagging.entity.Rule;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

@Repository
public interface RuleRepository extends CrudRepository<Rule, Integer> {
	
	@Query("SELECT r FROM Rule r WHERE r.target = 0 OR r.target = ?1")
    List<Rule> findByTarget(int target);
}