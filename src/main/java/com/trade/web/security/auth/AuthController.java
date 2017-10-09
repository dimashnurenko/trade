package com.trade.web.security.auth;

import com.trade.domain.user.UserService;
import com.trade.web.security.token.AccessToken;
import com.trade.web.user.UserDto;
import com.trade.web.user.UsersController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	                      @Qualifier("userServiceWrapper") UserService userService) {
		this.authService = authService;
		this.userService = userService;
	}

	@PostMapping(value = "/login", consumes = APPLICATION_JSON_VALUE)
	public AccessToken authenticate(@RequestBody AuthUserDto dto) {
		return authService.authenticate(dto);
	}

	@PostMapping(value = "/signup", consumes = APPLICATION_JSON_VALUE)
	public ResponseEntity createOne(@RequestBody UserDto dto) throws URISyntaxException {
		long userId = userService.createUser(dto);

		ResourceSupport resourceSupport = new ResourceSupport();
		resourceSupport.add(linkTo(methodOn(UsersController.class).findOne(userId)).withSelfRel());

		return status(CREATED)
				.location(new URI(resourceSupport.getLink("self").getHref()))
				.build();
	}

	@PostMapping(value = "/log-out")
	public ResponseEntity logout(@RequestParam Long userId) {
		authService.logout(userId);
		return status(OK).build();
	}
}
