package com.example.testtagging.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Embeddable 
@Table(name="rel_room_rule")
public class RoomRuleRel {
	@Column(name="rule_id")
	private String rule_id;

	@ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
}
