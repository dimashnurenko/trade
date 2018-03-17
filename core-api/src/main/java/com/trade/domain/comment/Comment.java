package com.trade.domain.comment;

import java.time.LocalDateTime;
import java.util.List;

public class Comment {
	private final Long id;
	private final String content;
	private final String createdBy;
	private final LocalDateTime createdDate;
	private final List<Comment> comments;

	private Comment(Long id, String content, String createdBy, LocalDateTime createdDate, List<Comment> comments) {
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

	public static final class Builder {
		private Long id;
		private String content;
		private String createdBy;
		private LocalDateTime createdDate;
		private List<Comment> comments;

		private Builder() {
		}

		public static Builder builder() {
			return new Builder();
		}

		public Builder id(Long id) {
			this.id = id;
			return this;
		}

		public Builder content(String content) {
			this.content = content;
			return this;
		}

		public Builder createdBy(String createdBy) {
			this.createdBy = createdBy;
			return this;
		}

		public Builder createdDate(LocalDateTime createdDate) {
			this.createdDate = createdDate;
			return this;
		}

		public Builder comments(List<Comment> comments) {
			this.comments = comments;
			return this;
		}

		public Comment build() {
			return new Comment(id, content, createdBy, createdDate, comments);
		}
	}
}
