package com.example.testtagging;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.example.testtagging.entity.RoomRule;

public interface RoomRuleRepository extends CrudRepository<RoomRule, Integer>{
	List<RoomRule> findAll();
}
