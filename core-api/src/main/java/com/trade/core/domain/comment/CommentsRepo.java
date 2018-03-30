package com.trade.core.domain.comment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentsRepo extends JpaRepository<CommentEntity, Long> {
	List<CommentEntity> findAllByProductIdAndCommentIdIsNull(Long productId);

	List<CommentEntity> findAllByCommentId(Long commentId);
}
