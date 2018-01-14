package com.trade.security.auth;

import com.trade.domain.user.User;
import com.trade.domain.user.UserDto;
import com.trade.domain.user.UserService;
import com.trade.security.token.AccessToken;
import com.trade.web.user.UserResource;
import com.trade.web.user.UsersController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.status;

@RestController
public class AuthController {
	private final AuthenticationService authService;
	private final UserService userService;

	@Autowired
	public AuthController(AuthenticationService authService,
	                      UserService userService) {
		this.authService = authService;
		this.userService = userService;
	}

	@PostMapping(value = "/login", consumes = APPLICATION_JSON_VALUE)
	public AccessToken authenticate(@RequestBody AuthUserDto dto) {
		return authService.authenticate(dto);
	}

	@PostMapping(value = "/signup", consumes = APPLICATION_JSON_VALUE)
	public ResponseEntity<UserResource> createOne(@RequestBody UserDto dto) throws URISyntaxException {
		User user = userService.createUser(dto);

		Link link = linkTo(methodOn(UsersController.class).findOne(user.getId())).withSelfRel();

		return status(CREATED)
				.location(new URI(link.getHref()))
				.body(new UserResource(user));
	}

	@PostMapping(value = "/log-out")
	public ResponseEntity logout(HttpServletRequest request) {
		String token = request.getHeader("Authentication");
		authService.logout(token);
		return status(OK).build();
	}
}
