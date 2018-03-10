package com.trade.domain.comment;

import java.time.LocalDateTime;
import java.util.List;

public class Comment {
	private final Long id;
	private final String content;
	private final String createdBy;
	private final LocalDateTime createdDate;
	private final List<Comment> comments;

	public Comment(Long id, String content, String createdBy, LocalDateTime createdDate, List<Comment> comments) {
		this.id = id;
		this.content = content;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.comments = comments;
	}

	public Long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public List<Comment> getComments() {
		return comments;
	}
}
