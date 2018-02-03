package com.trade.domain.comment;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
public class Comment {
	private final Long id;
	private final String content;
	private final String createdBy;
	private final LocalDateTime createdDate;
	private final List<Comment> comments;
}
