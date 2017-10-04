package com.trade.order;

import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;

import static com.trade.order.Status.NEW;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Getter
public class OrderResource extends ResourceSupport {
	private Long orderId;
	private Long userId;
	private String link;
	private String color;
	private String size;
	private Status status = NEW;

	public OrderResource(OrderDto dto) {
		this.orderId = dto.getId();
		this.userId = dto.getUserId();
		this.link = dto.getLink();
		this.color = dto.getColor();
		this.size = dto.getSize();
		this.status = dto.getStatus();

		add(linkTo(methodOn(OrdersController.class).findOne(orderId)).withSelfRel());
	}
}
