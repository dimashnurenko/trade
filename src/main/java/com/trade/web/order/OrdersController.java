package com.trade.web.order;

import com.trade.domain.order.OrderResource;
import com.trade.domain.order.OrderService;
import com.trade.domain.order.OrdersFilter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.ResourceSupport;
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

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/orders")
public class OrdersController {
	private final OrderService orderService;

	public OrdersController(@Qualifier("orderServiceWrapper") OrderService orderService) {
		this.orderService = orderService;
	}

	@GetMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
	public List<OrderResource> findByFilter(@RequestBody OrdersFilter filter) {
		List<OrderDto> orders = orderService.findByFilter(filter);
		return orders.stream().map(OrderResource::new).collect(toList());
	}

	@GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
	public OrderResource findOne(@PathVariable(value = "id") Long id) {
		return new OrderResource(orderService.findOne(id));
	}

	@PostMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
	public ResponseEntity createOne(@RequestBody OrderDto dto) throws URISyntaxException {
		long orderId = orderService.createOrder(dto);

		ResourceSupport resourceSupport = new ResourceSupport();
		resourceSupport.add(linkTo(methodOn(OrdersController.class).findOne(orderId)).withSelfRel());

		return status(CREATED)
				.location(new URI(resourceSupport.getLink("self").getHref()))
				.build();
	}

	@PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
	public ResponseEntity updateOne(@RequestBody OrderDto dto) {
		orderService.updateOrder(dto);
		return status(NO_CONTENT).build();
	}
}
