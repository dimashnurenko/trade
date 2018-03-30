package com.trade.core.domain.comment;

import com.trade.auth.user.UserService;
import com.trade.auth.user.model.User;
import com.trade.core.domain.product.Product;
import com.trade.core.domain.product.ProductService;
import com.trade.exception.ResourceNotFoundException;
import com.trade.exception.ValidationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.trade.core.domain.comment.Comment.Builder.builder;
import static com.trade.exception.ValidationErrorCodes.COMMENT_NOT_RELATED_TO_PROJECT;
import static java.util.stream.Collectors.toList;

@Component
public class CommentServiceImpl implements CommentService {
	private final CommentsRepo commentsRepo;
	private final ProductService productService;
	private final UserService userService;

	public CommentServiceImpl(CommentsRepo commentsRepo,
	                          ProductService productService,
	                          UserService userService) {
		this.commentsRepo = commentsRepo;
		this.productService = productService;
		this.userService = userService;
	}

	@Override
	@Transactional
	public Comment create(CommentDto dto, Long productId) {
		Product product = productService.findById(productId);

		CommentEntity comment = new CommentEntity();
		comment.setContent(dto.getContent());
		comment.setProductId(product.getId());

		CommentEntity commentEntity = commentsRepo.save(comment);
		return toModel(commentEntity);
	}

	@Override
	@Transactional
	public Comment create(CommentDto dto, Long productId, Long commentId) {
		CommentEntity commentEntity = getCommentEntity(productId, commentId);

		CommentEntity childComment = new CommentEntity();
		childComment.setCommentId(commentEntity.getId());
		childComment.setProductId(productId);
		childComment.setContent(dto.getContent());

		commentsRepo.save(childComment);

		return toModel(childComment);
	}

	private CommentEntity getCommentEntity(Long productId, Long commentId) {
		CommentEntity commentEntity = Optional.ofNullable(commentsRepo.findOne(commentId))
		                                      .orElseThrow(() -> new ResourceNotFoundException(commentId, "comment"));
		if (!productId.equals(commentEntity.getProductId())) {
			throw new ValidationException(COMMENT_NOT_RELATED_TO_PROJECT, "Comment is not related to project");
		}
		return commentEntity;
	}

	private Comment toModel(CommentEntity entity) {
		User user = userService.findOne(entity.getCreatedBy());
		return builder().id(entity.getId())
		                .content(entity.getContent())
		                .createdBy(user.getName())
		                .createdDate(entity.getCreatedDate())
		                .comments(commentsRepo.findAllByCommentId(entity.getId())
		                                      .stream()
		                                      .map(this::toModel)
		                                      .collect(toList()))
		                .build();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Comment> findByProductId(Long productId) {
		List<CommentEntity> comments = commentsRepo.findAllByProductIdAndCommentIdIsNull(productId);
		return comments.stream().map(this::toModel).collect(toList());
	}

	@Override
	@Transactional(readOnly = true)
	public Comment findComment(Long productId, Long commentId) {
		return toModel(getCommentEntity(productId, commentId));
	}
}
