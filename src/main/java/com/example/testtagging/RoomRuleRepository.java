package com.example.testtagging;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.example.testtagging.entity.RoomRule;
import com.example.testtagging.entity.RoomRuleCompositeKey;

public interface RoomRuleRepository extends CrudRepository<RoomRule, RoomRuleCompositeKey>{
	List<RoomRule> findAll();
	
	@Query(value = "SELECT rr.rule_id from rel_room_rule rr WHERE rr.room_id = ?1", nativeQuery = true)
	List<Integer> findRuleByRoomId(int roomId);
}
