package com.trade.web.buyer;

import com.trade.domain.buyer.BuyerService;
import com.trade.domain.buyer.settings.BuyerSettingsEntity;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.created;

@RestController
@RequestMapping(name = "/api/v1/buyers/{buyerId}/settings")
public class BuyerSettingController {

	private final BuyerService buyerService;

	@Autowired
	public BuyerSettingController(BuyerService buyerService) {
		this.buyerService = buyerService;
	}

	@ApiResponses({
			              @ApiResponse(code = 201, message = "buyer settings created"),
			              @ApiResponse(code = 400, message = "invalid request data"),
			              @ApiResponse(code = 401, message = "unauthorized"),
			              @ApiResponse(code = 500, message = "server error")
	              })
	@PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
	public ResponseEntity<BuyerSettingsResource> createSettings(@RequestBody BuyerSettingsDto dto) throws URISyntaxException {
		BuyerSettingsEntity entity = buyerService.create(dto);

		Link link = linkTo(methodOn(BuyerSettingController.class).findSetting(entity.getBuyerId(), entity.getId())).withSelfRel();
		return created(new URI(link.getHref())).body(new BuyerSettingsResource(entity));
	}

	@ApiResponses({
			              @ApiResponse(code = 200, message = "buyer settings updated"),
			              @ApiResponse(code = 401, message = "unauthorized"),
			              @ApiResponse(code = 404, message = "not found"),
			              @ApiResponse(code = 500, message = "server error")
	              })
	@PutMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
	public ResponseEntity<BuyerSettingsResource> updateSettings(@PathVariable Long id, @RequestBody BuyerDto dto) {
		throw new UnsupportedOperationException("not supported yet");
	}

	@ApiResponses({
			              @ApiResponse(code = 200, message = "ok"),
			              @ApiResponse(code = 401, message = "unauthorized"),
			              @ApiResponse(code = 404, message = "not found"),
			              @ApiResponse(code = 500, message = "server error")
	              })
	@GetMapping(produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BuyerSettingsResource>> findSettings(@PathVariable Long buyerId) {
		throw new UnsupportedOperationException("not supported yet");
	}

	@ApiResponses({
			              @ApiResponse(code = 200, message = "ok"),
			              @ApiResponse(code = 401, message = "unauthorized"),
			              @ApiResponse(code = 404, message = "not found"),
			              @ApiResponse(code = 500, message = "server error")
	              })
	@GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BuyerSettingsResource>> findSetting(@PathVariable Long buyerId, @PathVariable Long id) {
		throw new UnsupportedOperationException("not supported yet");
	}
}