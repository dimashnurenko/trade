package com.trade.auth.web;

import com.trade.exception.AuthException;
import com.trade.auth.AuthUserDto;
import com.trade.auth.AuthenticationService;
import com.trade.auth.token.AuthTokenDto;
import com.trade.auth.user.model.User;
import com.trade.auth.user.model.UserDto;
import com.trade.auth.user.UserResource;
import com.trade.auth.user.UserService;
import com.trade.auth.user.UsersController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;
import java.net.URISyntaxException;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@Controller
@RequestMapping("/api/v1/auth")
public class AuthController {
	private final AuthenticationService authService;
	private final UserService userService;

	@Autowired
	public AuthController(AuthenticationService authService,
	                      UserService userService) {
		this.authService = authService;
		this.userService = userService;
	}

	@PostMapping(value = "/token", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<AuthTokenDto> authenticate(@RequestBody AuthUserDto dto) {
		return ResponseEntity.ok(new AuthTokenDto(authService.authenticate(dto)));
	}

	@GetMapping(produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<UserInfoDto> authenticate(@RequestHeader(value = "Authentication") String token) {
		return ok(authService.authenticate(token));
	}

	@PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<UserResource> createOne(@RequestBody UserDto dto) throws URISyntaxException {
		User user = userService.createUser(dto);

		Link link = linkTo(methodOn(UsersController.class).findOne(user.getId())).withSelfRel();

		return status(CREATED)
				.location(new URI(link.getHref()))
				.body(new UserResource(user));
	}

	@DeleteMapping
	public ResponseEntity logout(@RequestHeader(value = "Authentication") String token) {
		authService.logout(token);
		return status(OK).build();
	}

	@ExceptionHandler
	public ResponseEntity handleAuthException(AuthException e) {
		return ResponseEntity.status(UNAUTHORIZED).body(e.getMessage());
	}
}
