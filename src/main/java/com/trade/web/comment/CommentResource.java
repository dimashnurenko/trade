package com.trade.web.comment;

import com.trade.domain.comment.Comment;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class CommentResource extends ResourceSupport {
	private Comment comment;

	public CommentResource(Comment comment, Long productId) {
		this.comment = comment;

		add(linkTo(methodOn(CommentsController.class).findAllByProductId(productId)).withRel("product-comments"));
		add(linkTo(methodOn(CommentsController.class).findComment(productId, comment.getId())).withSelfRel());
	}

	public Comment getComment() {
		return comment;
	}
}
