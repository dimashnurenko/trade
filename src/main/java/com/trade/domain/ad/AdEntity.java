package com.trade.domain.ad;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

import static javax.persistence.EnumType.STRING;

@Getter
@Setter
@Entity
@Table(name = "ad")
public class AdEntity {
	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "group_id")
	private Long groupId;
	@Column
	private String link;
	@Column
	private String title;
	@Column
	private BigDecimal price;
	@Column(name = "image_url")
	private String imageUrl;
	@Enumerated(value = STRING)
	@Column(name = "status")
	private AdStatus status;
}
