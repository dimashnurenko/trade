package com.trade.web.buyer;

import com.trade.domain.buyer.BuyerEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Getter
@Setter
public class BuyerResource extends ResourceSupport {
	private Long buyerId;
	private Long userId;
	private String name;
	private String phone;

	public BuyerResource(BuyerEntity entity) {
		this.buyerId = entity.getId();
		this.userId = entity.getUserId();
		this.name = entity.getName();
		this.phone = entity.getPhone();

		add(linkTo(methodOn(BuyerController.class).findOne(buyerId)).withSelfRel());
		add(linkTo(methodOn(BuyerSettingController.class).findSettings(buyerId)).withRel("settings"));
	}
}
