package com.trade.web.order;

import com.trade.domain.order.Order;
import com.trade.domain.order.Status;
import com.trade.web.ad.AdController;
import com.trade.web.group.GroupController;
import org.springframework.hateoas.ResourceSupport;

import java.math.BigDecimal;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

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

	public String getCustomerName() {
		return customerName;
	}

	public String getAdTitle() {
		return adTitle;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public Status getStatus() {
		return status;
	}
}
