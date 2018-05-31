package com.trade.auth.web;

import com.trade.auth.AuthUserDto;
import com.trade.auth.AuthenticationService;
import com.trade.auth.token.AuthTokenDto;
import com.trade.auth.user.UserService;
import com.trade.auth.user.model.User;
import com.trade.auth.user.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.http.HttpStatus.OK;
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
		return ok(new AuthTokenDto(authService.authenticate(dto)));
	}

	@GetMapping(produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<UserInfoDto> authenticate(@RequestHeader(value = "Authentication") String token) {
		return ok(authService.authenticate(token));
	}

	@PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<AuthTokenDto> createOne(@RequestBody UserDto dto) {
		User user = userService.createUser(dto);
		return ok(new AuthTokenDto(authService.authenticate(new AuthUserDto(user.getPhone(), user.getPassword()))));
	}

	@DeleteMapping
	public ResponseEntity logout(@RequestHeader(value = "Authentication") String token) {
		authService.logout(token);
		return status(OK).build();
	}
}
