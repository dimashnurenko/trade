package com.trade.domain.comment;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "comments")
@EntityListeners(AuditingEntityListener.class)
public class CommentEntity {
	@Id
	@GeneratedValue
	private Long id;

	@Column
	private String content;

	@Column(name = "product_id")
	private Long productId;

	@Column(name = "created_date")
	@CreatedDate
	private LocalDateTime createdDate;

	@Column(name = "updated_date")
	@LastModifiedDate
	private LocalDateTime updatedDate;

	@Column(name = "created_by")
	@CreatedBy
	private Long createdBy;
	//todo audit move to separate classes
	@Column(name = "updated_by")
	@LastModifiedBy
	private Long updatedBy;

	@Column(name = "comment_id")
	private Long commentId;
}
