package com.trade.web.order;

import com.trade.domain.order.Order;
import com.trade.domain.order.Status;
import com.trade.web.ad.AdController;
import com.trade.web.group.GroupController;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;

import java.math.BigDecimal;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Getter
@Setter
public class OrderResource extends ResourceSupport {
	private String customerName;
	private String adTitle;
	private BigDecimal total;
	private Status status;

	public OrderResource(Order order) {
		this.customerName = order.getCustomerName();
		this.adTitle = order.getAdTitle();
		this.total = order.getTotal();
		this.status = order.getStatus();

		add(linkTo(methodOn(OrderController.class).findOne(order.getOrderId())).withSelfRel());
		add(linkTo(methodOn(AdController.class).findOne(order.getAdId())).withRel("ad"));
		add(linkTo(methodOn(GroupController.class).findOne(order.getGroupId())).withRel("group"));
	}
}
