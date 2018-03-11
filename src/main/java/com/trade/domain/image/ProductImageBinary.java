package com.trade.domain.image;

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
@Table(name = "product_image_binary")
public class ProductImageBinary {
	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "product_image_id")
	private Long productImageId;
	@Column(name = "product_id")
	private Long productId;
	@Column(name = "data")
	private byte[] data;
}
