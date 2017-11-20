package com.trade.web.order;

import com.trade.domain.order.Order;
import com.trade.domain.order.OrderService;
import com.trade.web.user.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
	private final OrderService orderService;

	@Autowired
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@PostMapping(produces = APPLICATION_JSON_VALUE)
	public ResponseEntity createOrder(@RequestParam Long adId, LoggedUser customer) throws URISyntaxException {
		Long orderId = orderService.create(adId, customer);
		Link link = linkTo(methodOn(OrderController.class).findOne(orderId)).withSelfRel();
		return created(new URI(link.getHref())).build();
	}

	@GetMapping(path = "/{orderId}")
	public ResponseEntity<OrderResource> findOne(@PathVariable Long orderId) {
		Order order = orderService.findOne(orderId);
		return ok(new OrderResource(order));
	}
}
