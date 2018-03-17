package com.trade.web.image;

import org.springframework.hateoas.ResourceSupport;

import static java.lang.String.format;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class ImagesResource extends ResourceSupport {

	private final Long imageId;

	public ImagesResource(Long imageId, Long productId) {
		this.imageId = imageId;
		add(linkTo(methodOn(ImagesController.class).image(productId, imageId)).withRel(format("product_%s_image", productId)));
		add(linkTo(methodOn(ImagesController.class).upload(productId, null)).withRel("upload_image"));
	}

	public Long getImageId() {
		return imageId;
	}
}
