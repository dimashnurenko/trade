package com.trade.web.settings;

import com.trade.domain.user.settings.UserSettings;
import com.trade.domain.user.settings.UserSettingsService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.created;

@RestController
@RequestMapping(name = "/api/v1/users/{userId}/settings")
public class UserSettingController {

	private final UserSettingsService settingsService;

	@Autowired
	public UserSettingController(UserSettingsService settingsService) {
		this.settingsService = settingsService;
	}

	@ApiResponses({
			              @ApiResponse(code = 201, message = "buyer settings created"),
			              @ApiResponse(code = 400, message = "invalid request data"),
			              @ApiResponse(code = 401, message = "unauthorized"),
			              @ApiResponse(code = 500, message = "server error")
	              })
	@PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
	public ResponseEntity<UserSettingsResource> createSettings(@PathVariable Long userId, @RequestBody UserSettingsDto dto) throws URISyntaxException {
		UserSettings settings = settingsService.create(userId, dto);

		UserSettingsResource resource = new UserSettingsResource(settings);
		return created(new URI(resource.getLink("self").getHref())).body(resource);
	}

	@ApiResponses({
			              @ApiResponse(code = 200, message = "buyer settings updated"),
			              @ApiResponse(code = 401, message = "unauthorized"),
			              @ApiResponse(code = 404, message = "not found"),
			              @ApiResponse(code = 500, message = "server error")
	              })
	@PutMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
	public ResponseEntity<UserSettingsResource> updateSettings(@PathVariable Long id, @RequestBody UserSettingsDto dto) {
		throw new UnsupportedOperationException("not supported yet");
	}

	@ApiResponses({
			              @ApiResponse(code = 200, message = "ok"),
			              @ApiResponse(code = 401, message = "unauthorized"),
			              @ApiResponse(code = 404, message = "not found"),
			              @ApiResponse(code = 500, message = "server error")
	              })
	@GetMapping(produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserSettingsResource>> findSettings(@PathVariable Long userId) {
		throw new UnsupportedOperationException("not supported yet");
	}

	@ApiResponses({
			              @ApiResponse(code = 200, message = "ok"),
			              @ApiResponse(code = 401, message = "unauthorized"),
			              @ApiResponse(code = 404, message = "not found"),
			              @ApiResponse(code = 500, message = "server error")
	              })
	@GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserSettingsResource>> findSetting(@PathVariable Long buyerId, @PathVariable Long id) {
		throw new UnsupportedOperationException("not supported yet");
	}
}
