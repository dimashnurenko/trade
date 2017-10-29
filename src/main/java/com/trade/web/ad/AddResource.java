package com.trade.web.ad;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;

@Getter
@Setter
public class AddResource extends ResourceSupport {
	private Long addId;
	private String link;
	private String title;
	private String price;
	private String imageUrl;
}
