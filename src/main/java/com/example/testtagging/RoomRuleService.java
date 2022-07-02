package com.example.testtagging;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.testtagging.entity.RoomRule;

@Service
public class RoomRuleService {

	private final RoomRuleRepository roomRuleRepository;
	
	@Autowired
	public RoomRuleService(RoomRuleRepository roomRuleRepository) {
		this.roomRuleRepository = roomRuleRepository;
	}
	public List<RoomRule> findAll() {
		return this.roomRuleRepository.findAll();
	}
	
	public List<Integer> findRuleByRoomId(int roomId){
		return this.roomRuleRepository.findRuleByRoomId(roomId);
	}
}