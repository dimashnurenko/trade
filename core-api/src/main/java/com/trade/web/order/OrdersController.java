package com.trade.web.order;

import com.trade.domain.order.Order;
import com.trade.domain.order.OrderService;
import com.trade.web.user.LoggedUser;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;
import java.net.URISyntaxException;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;

@Controller
@RequestMapping("/api/v1/orders")
public class OrdersController {

	private final OrderService orderService;

	public OrdersController(OrderService orderService) {
		this.orderService = orderService;
	}

	@PostMapping(consumes = APPLICATION_JSON_VALUE)
	public ResponseEntity makeOrder(LoggedUser loggedUser, @RequestBody CreateOrderDto dto) throws URISyntaxException {
		Long orderId = orderService.create(dto, loggedUser.getId());

		Link orderDetailsUrl = linkTo(methodOn(OrdersController.class).findOne(orderId, loggedUser)).withRel("order_details");
		return created(new URI(orderDetailsUrl.getHref())).build();
	}

	@GetMapping(path = "/{orderId}", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<OrderResource> findOne(@PathVariable Long orderId, LoggedUser user) {
		Order order = orderService.findOne(orderId);
		return ok(new OrderResource(order));
	}
}
