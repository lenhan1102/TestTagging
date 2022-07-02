package com.example.testtagging;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.TestInstance;

import com.example.testtagging.entity.Room;
import com.example.testtagging.entity.RoomRule;
import com.example.testtagging.entity.Rule;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TaggingTest {
	int c;
    private  TaggingService taggingService;
	private  RoomService roomService;
	private  RuleService ruleService;
	private  RoomRuleService roomRuleService;
	
	@Autowired
	public TaggingTest(TaggingService taggingService,  RoomService roomService, RuleService ruleService, RoomRuleService roomRuleService){
		this.taggingService = taggingService;
		this.roomService = roomService;
		this.ruleService = ruleService;	
		this.roomRuleService = roomRuleService;
		c = 1;
	}

	@ParameterizedTest
	@MethodSource("getParamsSource")
	void handleTaggingSingleRoomTest(TaggingParams params) {
		Room room = params.getRoom();
		List<Rule> rules = params.getRules();
		List<Integer> expectedValue = params.getRes();
		List<Integer> testedValue = taggingService.handleTaggingSingleRoom(room, rules).getRuleIds();
//		System.out.print(c++ + " ");
//		System.out.print(testedValue); 
//		System.out.print("/ ");
//		System.out.println(expectedValue);
		
		assertTrue(CollectionUtils.isEqualCollection(testedValue, expectedValue));
	}
	private List<TaggingParams> getParamsSource() {
		List<TaggingParams> allParams = new ArrayList<>();
		List<Rule> allRules = ruleService.findAll();
		List<Room> rooms = roomService.findAll();	
		List<RoomRule> allRoomRule = roomRuleService.findAll();
		
		for (int i = 0; i < rooms.size(); i++) {
			TaggingParams params = new TaggingParams();
			Room room = rooms.get(i);

			params.setRoom(room);
			
//			List<Rule> tmpRules = allRules.stream()
//					.filter(rule -> rule.getTarget() == 0 || rule.getTarget() == room.getHotelId())
//					.collect(Collectors.toList());
//			params.setRules(tmpRules);
			List<Rule> tmpRules = ruleService.findByTarget(room.getHotelId());
			params.setRules(tmpRules);
			
			
//			List<Integer> expected = allRoomRule.stream()
//					.filter(roomrule -> roomrule.getRoom() == room.getId())
//					.map(roomrule -> {return roomrule.getRule();})
//					.collect(Collectors.toList());
//			params.setRes(expected);
			params.setRes(roomRuleService.findRuleByRoomId(room.getId()));
			
			allParams.add(params);
		}
		return allParams;
	}	
}

