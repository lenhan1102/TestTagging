package com.example.testtagging.entity;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

import javax.persistence.*;

@MappedSuperclass
@Getter
@Setter
public class BaseRoom {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int id;
	protected String name;
	protected String originalName;
	protected int hotelId;
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    // Quan hệ n-n với đối tượng ở dưới (Person) (1 địa điểm có nhiều người ở)
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()

    @JoinTable(name = "rel_room_rule", //Tạo ra một join Table tên là "rel_room_rule"
            joinColumns = @JoinColumn(name = "room_id"),  // TRong đó, khóa ngoại chính là address_id trỏ tới class hiện tại (Address)
            inverseJoinColumns = @JoinColumn(name = "rule_id") //Khóa ngoại thứ 2 trỏ tới thuộc tính ở dưới (Person)
    )
	protected List<Rule> rules;
}
