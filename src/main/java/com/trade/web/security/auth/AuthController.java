package com.trade.web.security.auth;

import com.trade.web.security.token.AuthTokenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.status;

@RestController
public class AuthController {
	private final AuthenticationService authService;

	@Autowired
	public AuthController(AuthenticationService authService) {
		this.authService = authService;
	}

	@PostMapping(value = "/login", consumes = APPLICATION_JSON_VALUE)
	public AuthTokenDto authenticate(@RequestBody AuthUserDto dto) {
		return authService.authenticate(dto);
	}

	@PostMapping(value = "/logout", consumes = APPLICATION_JSON_VALUE)
	public ResponseEntity logout(@RequestParam Long userId) {
		authService.logout(userId);
		return status(OK).build();
	}
}
