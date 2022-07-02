package com.example.testtagging;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.testtagging.entity.BaseRoom;
import com.example.testtagging.entity.Rule;
import com.google.common.collect.ComparisonChain;

@Service
@Qualifier
public class TaggingServiceImp implements TaggingService{
	private final int MAX_NUMBER_OF_RULES = 3000;
	
	@Override
	public <T extends BaseRoom> Map<Integer, List<Integer>> tagRoom(List<T> rooms, List<Rule> rules) {
		Map<Integer, List<Integer>> res = new HashMap<Integer, List<Integer>>();
		
		//Define a list stores all rules can be applied to each room
		List<Rule> ruleList = new ArrayList<>();
		
		//Call handleTaggingSingleRoom for each room and applicable ruleList
		for (T room : rooms) {
			int hotelId = room.getHotelId();
			for (Rule rule : rules) {
				if ((rule.getTarget() == 0 || rule.getTarget() == hotelId) && room.getName().indexOf(rule.getInput()) >= 0) {
					ruleList.add(rule);
				}
				else continue;
			}
			TaggingResult tr = handleTaggingSingleRoom(room, ruleList);
			res.put(room.getId(), tr.getRuleIds());
			ruleList.clear();
		}
		return res;
	}
	
	@Override
	public TaggingResult handleTaggingSingleRoom(BaseRoom room, List<Rule> rules) {	
		TaggingResult res = new TaggingResult(room.getId());
		if (rules.isEmpty()) {
			res.setRoomId(room.getId());
			res.setRuleIds(null);
			return res;
		}
		
		List<Integer> ruleIds = new ArrayList<>();	
		
		//Change rules to a modifiable list for sorting
		List<Rule> tmpRules = new ArrayList<>(rules);
		
		String roomName = room.getName();
		//Store substring index of each rule to avoid recalculating roomName.indexOf()
		int[] substringIndexArr = new int[MAX_NUMBER_OF_RULES];
		for (int i = 0; i < tmpRules.size(); i++) {
			int id = tmpRules.get(i).getId();
			substringIndexArr[id] = roomName.indexOf(tmpRules.get(i).getInput());
		}
		int[] mark = new int[roomName.length()];
	
		Collections.sort(tmpRules, new RuleComparator(substringIndexArr));
		
		for (int i = 0; i < tmpRules.size(); i++) {
			if (isApplicable(roomName, tmpRules.get(i), mark)) {
				ruleIds.add(tmpRules.get(i).getId());
				if (i+1 >= tmpRules.size() || !tmpRules.get(i).getInput().equals(tmpRules.get(i+1).getInput())) {
					String s = tmpRules.get(i).getInput();
					int start = roomName.indexOf(s);
					int end = start + s.length();
					for (int idx = start; idx < end; idx++) {
						mark[idx] = 1;
					}
				}
			}
        }
		Collections.sort(ruleIds);
		res.setRuleIds(ruleIds);
		return res;
	}
	
	/**
	 * check if Rule rule can be applied to String s
	 * @param s: String which rule applied to
	 * @param rule: a rule
	 * @param mark: array that stores positions in String that can be applied or not (position that is used by another rule)
	 * @return
	 */
	
	private static boolean isApplicable(String string, Rule rule, int[] mark) {
		String sub = rule.getInput();
		
		int start = string.indexOf(sub);
		int end = start + sub.length() - 1;
		if (start < 0 || start + sub.length() > string.length()) return false;
		
		while (start >= 0) {
			end = start + sub.length()-1;
			if ((start == 0 || string.charAt(start-1) == ' ') && (end == string.length() - 1 || string.charAt(end+1) == ' ')) {
				int i;
				for (i = 0; i < sub.length(); i++) {
					if (mark[start+i] == 1 || string.charAt(start+i) != sub.charAt(i)) break;
				}
				if (i == sub.length()) return true;
			} else {
				if (string.indexOf(sub, start+1) < 0) return false;
			}
			//find next appearance
			start = string.indexOf(sub, start+1);
		}
		return false;
	}
	
	private class RuleComparator implements Comparator<Rule>{
		private int[] substringIndexArr = new int[MAX_NUMBER_OF_RULES];
		RuleComparator(int[] substringIndexArr){
			this.substringIndexArr = substringIndexArr;
		}	
		@Override
		public int compare(Rule r1, Rule r2) {
			int res = ComparisonChain.start()
			.compare(r2.getTarget(), r1.getTarget())
			.compare(r2.getRule_size(), r1.getRule_size())
			.compare(this.substringIndexArr[r1.getId()], this.substringIndexArr[r2.getId()])
			.result(); 
			return res;
		}
		
	}

}



