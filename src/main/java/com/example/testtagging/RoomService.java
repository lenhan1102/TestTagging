package com.example.testtagging;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.testtagging.entity.Room;

@Service
public class RoomService {
	private final RoomRepository roomRepository;
	private final RoomRuleRepository roomRuleRepository;

	@Autowired
	public RoomService(RoomRepository roomRepository, RoomRuleRepository roomRuleRepository) {
		this.roomRepository = roomRepository;
		this.roomRuleRepository = roomRuleRepository;
	}
	public void addRuleUpdate(String dimension, String value, Integer target, String input, Integer rule_size) {
		Optional<Room> list;
		if (target != 0) {
			list = roomRepository.findById(target);
		} else {
			
		}
	}
	public List<Room> findAll() {
		return this.roomRepository.findAll();
	}
	public Optional<Room> findRoomById(Integer id) {
		return roomRepository.findById(id);
	}
}
