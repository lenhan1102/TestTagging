package com.example.testtagging.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

public class RoomRuleCompositeKey implements Serializable {
	private int rule;
    private int room;
}
