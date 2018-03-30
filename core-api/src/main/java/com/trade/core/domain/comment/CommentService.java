package com.trade.core.domain.comment;

import java.util.List;

public interface CommentService {

	Comment create(CommentDto dto, Long productId);

	Comment create(CommentDto dto, Long productId, Long commentId);

	List<Comment> findByProductId(Long productId);

	Comment findComment(Long productId, Long commentId);
}
