package com.example.testtagging;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.testtagging.RoomRepository;
import com.example.testtagging.TaggingResult;
import com.example.testtagging.TaggingService;
import com.example.testtagging.entity.Room;
import com.example.testtagging.entity.Rule;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class TaggingServiceImpTest {
	

	@Autowired
    private TaggingService taggingService;
	
	@Autowired
	private RoomService roomService;
	
	@ParameterizedTest
	@MethodSource({"getRoomSource", "getRulesSource", "getResSource"})
	void test(Room room, List<Rule> rules, List<Integer> res) {
		assertTrue(CollectionUtils.isEqualCollection(taggingService.handleTaggingSingleRoom(room, rules).getRuleIds(), res));
	}
	
	private List<Room> getRoomSource() {
		return roomService.findAll();
	}			
}
