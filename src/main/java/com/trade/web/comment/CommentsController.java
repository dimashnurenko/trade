package com.trade.web.comment;

import com.trade.domain.comment.Comment;
import com.trade.domain.comment.CommentDto;
import com.trade.domain.comment.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

@Controller
@RequestMapping("/api/v1/products/{productId}/comments")
public class CommentsController {
	private final CommentService commentService;

	public CommentsController(CommentService commentService) {
		this.commentService = commentService;
	}

	@PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<CommentResource> create(@RequestBody CommentDto dto, @PathVariable Long productId) {
		Comment comment = commentService.create(dto, productId);
		return ok(new CommentResource(comment, productId));
	}

	@PostMapping(path = "/{commentId}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<CommentResource> create(@RequestBody CommentDto dto,
	                                              @PathVariable Long productId,
	                                              @PathVariable Long commentId) {
		Comment comment = commentService.create(dto, productId, commentId);
		return ok(new CommentResource(comment, productId));
	}

	@GetMapping(produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CommentResource>> findAllByProductId(@PathVariable Long productId) {
		List<Comment> comments = commentService.findByProductId(productId);
		return ok(comments.stream().map(it -> new CommentResource(it, productId)).collect(toList()));
	}

	@GetMapping(path = "/{commentId}", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<CommentResource> findComment(@PathVariable Long productId, @PathVariable Long commentId) {
		Comment comment = commentService.findComment(productId, commentId);
		return ok(new CommentResource(comment, productId));
	}
}
