package com.trade.web.user;

import com.trade.domain.user.UserResource;
import com.trade.domain.user.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController {
	private final UserService userService;

	public UsersController(@Qualifier("userServiceWrapper") UserService userService) {
		this.userService = userService;
	}

	@PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<UserResource> create(UserDto dto) throws URISyntaxException {
		UserResource resource = new UserResource(userService.createUser(dto));

		Link link = linkTo(methodOn(UsersController.class).findOne(resource.getUserId())).withSelfRel();
		return ResponseEntity.created(new URI(link.getHref())).body(resource);
	}

	@GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
	public UserResource findOne(@PathVariable(value = "id") Long id) {
		return new UserResource(userService.findOne(id));
	}

	@PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
	public ResponseEntity updateOne(@RequestBody UserDto dto) {
		userService.updateUser(dto);
		return status(NO_CONTENT).build();
	}
}
