package com.trade.domain.comment;

import com.trade.common.audit.Audit;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "comments")
@EntityListeners(AuditingEntityListener.class)
public class CommentEntity extends Audit {
	@Id
	@GeneratedValue
	private Long id;

	@Column
	private String content;

	@Column(name = "product_id")
	private Long productId;

	@Column(name = "comment_id")
	private Long commentId;
}
