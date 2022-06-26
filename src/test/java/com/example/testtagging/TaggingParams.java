package com.example.testtagging;
import com.example.testtagging.entity.Room;
import com.example.testtagging.entity.Rule;

import java.util.ArrayList;
import java.util.List;

public class TaggingParams {
	private Room room;
	private List<Rule> rules = new ArrayList<>();
	private List<Integer> res = new ArrayList<>();

	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public List<Rule> getRules() {
		return rules;
	}
	public void setRules(List<Rule> rules) {
		this.rules = List.copyOf(rules);
	}
	public List<Integer> getRes() {
		return res;
	}
	public void setRes(List<Integer> res) {
		this.res = List.copyOf(res);
	}
}
