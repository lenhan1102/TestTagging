package com.example.testtagging;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.testtagging.entity.BaseRoom;
import com.example.testtagging.entity.Rule;
import com.google.common.collect.ComparisonChain;

@Service
@Qualifier
public class TaggingServiceImp implements TaggingService{
	
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
			res.setRuleIds(null);
			return res;
		}
		
		List<Integer> ruleIds = new ArrayList<>();	
		//List<Rule> tmpRules = new ArrayList<>();
		
		String roomName = room.getName();	
		int[] mark = new int[roomName.length()];
	
		Collections.sort(rules, new RuleComparator(roomName));
		for (int index = 0; index < rules.size(); index++) {
			if (isApplicable(roomName, rules.get(index), mark)) {
				ruleIds.add(rules.get(index).getId());
			}
        }
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
	private static boolean isApplicable(String s, Rule rule, int[] mark) {
		String substring = rule.getInput();
		int posStart = s.indexOf(substring);
		if (posStart < 0 || posStart + substring.length() > s.length()) return false;
		for (int i = 0; i < substring.length(); i++) {
			if (mark[posStart+i] == 1 || s.charAt(posStart+i) != substring.charAt(i)) return false;
		}
		for (int i = posStart; i < posStart+substring.length(); i++) {
			mark[i] = 1;
		}
		return true;
	}
	
	private class RuleComparator implements Comparator<Rule>{
		private String s;
		RuleComparator(String s){
			this.s = s;
		}	
		@Override
		public int compare(Rule r1, Rule r2) {
			int res = ComparisonChain.start()
			.compare(r2.getTarget(), r1.getTarget())
			.compare(r2.getInput().length(), r1.getInput().length())
			.compare(this.s.indexOf(r1.getInput()), this.s.indexOf(r2.getInput()))
			.result(); 
			return res;
		}
		
	}

}

//시티뷰 슈페리어 더블 타임딜 시티뷰 파셜오션뷰 업그레이드
//오션
//오션뷰
