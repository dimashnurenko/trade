package com.trade.common.audit;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public class Audit {
	@CreatedDate
	@Column(name = "created_date")
	private LocalDateTime createdDate;

	@Column(name = "created_by")
	@CreatedBy
	private Long createdBy;

	@Column(name = "updated_date")
	@LastModifiedDate
	private LocalDateTime updatedDate;

	@Column(name = "updated_by")
	@LastModifiedBy
	private Long updatedBy;
}
