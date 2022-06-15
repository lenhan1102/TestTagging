package com.example.testtagging;

import java.util.List;
import java.util.Map;

import com.example.testtagging.entity.BaseRoom;
import com.example.testtagging.entity.Rule;

public interface TaggingService {
    <T extends BaseRoom> Map<Integer, List<Integer>> tagRoom(List<T> rooms, List<Rule> rules);

    TaggingResult handleTaggingSingleRoom(BaseRoom room, List<Rule> rules);
}
