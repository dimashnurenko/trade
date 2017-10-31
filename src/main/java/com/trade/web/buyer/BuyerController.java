package com.trade.web.buyer;

import com.trade.domain.buyer.BuyerEntity;
import com.trade.domain.buyer.BuyerService;
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

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.created;

@RestController
@RequestMapping("/api/v1/buyers")
public class BuyerController {

	private final BuyerService buyerService;

	@Autowired
	public BuyerController(BuyerService buyerService) {
		this.buyerService = buyerService;
	}

	@ApiResponses({
			              @ApiResponse(code = 201, message = "buyer created"),
			              @ApiResponse(code = 400, message = "invalid request data"),
			              @ApiResponse(code = 401, message = "unauthorized"),
			              @ApiResponse(code = 500, message = "server error")
	              })
	@PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
	public ResponseEntity<BuyerResource> create(@RequestBody BuyerDto dto) throws URISyntaxException {
		BuyerEntity entity = buyerService.create(dto);

		Link link = linkTo(methodOn(BuyerController.class).findOne(entity.getId())).withSelfRel();
		return created(new URI(link.getHref())).body(new BuyerResource(entity));
	}

	@ApiResponses({
			              @ApiResponse(code = 200, message = "ok"),
			              @ApiResponse(code = 401, message = "unauthorized"),
			              @ApiResponse(code = 404, message = "not found"),
			              @ApiResponse(code = 500, message = "server error")
	              })
	@GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<BuyerResource> findOne(@PathVariable Long id) {
		throw new UnsupportedOperationException("not supported yet");
	}

	@ApiResponses({
			              @ApiResponse(code = 200, message = "buyer updated"),
			              @ApiResponse(code = 401, message = "unauthorized"),
			              @ApiResponse(code = 404, message = "not found"),
			              @ApiResponse(code = 500, message = "server error")
	              })
	@PutMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
	public ResponseEntity<BuyerResource> update(@PathVariable Long id, @RequestBody BuyerDto dto) {
		throw new UnsupportedOperationException("not supported yet");
	}
}