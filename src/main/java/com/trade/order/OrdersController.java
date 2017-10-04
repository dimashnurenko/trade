package com.trade.order;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/orders")
public class OrdersController {
	private final OrderService orderService;

	public OrdersController(OrderService orderService) {
		this.orderService = orderService;
	}

	@GetMapping(produces = APPLICATION_JSON_VALUE)
	public List<OrderDto> findByFilter(@RequestBody OrdersFilter filter) {
		return null;
	}

	@GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
	public OrderResource findOne(@PathVariable(value = "id") Long id) {
		return new OrderResource(orderService.findOne(id));
	}

	@PostMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
	public OrderDto createOne(@RequestBody OrderDto dto) {
		return null;
	}

	@PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
	public OrderDto updateOne(OrderDto dto) {
		return null;
	}
}
