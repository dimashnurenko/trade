package com.trade.domain.group;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "ad_group")
public class Group {
	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "user_id")
	private Long userId;
	@Column
	private String name;
}
