package com.trade.domain.product;

import com.trade.common.audit.CreateAudit;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

import static com.trade.domain.product.ProductStatus.NEW;
import static javax.persistence.EnumType.STRING;

@Getter
@Setter
@Entity
@Table(name = "product")
@EntityListeners(AuditingEntityListener.class)
public class ProductEntity extends CreateAudit {

	@GeneratedValue
	@Id
	private Long id;

	@Column
	private String title;

	@Column
	private String description;

	@Column
	private BigDecimal price;

	@Column(name = "default_image_id")
	private Long defaultImageId;

	@Column
	@Enumerated(value = STRING)
	private ProductStatus status = NEW;

	@Column(name = "feed_id")
	private Long feedId;
}
