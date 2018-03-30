package com.trade.web.product;

import com.trade.core.domain.product.Product;
import com.trade.web.image.ImagesController;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class ProductResource extends ResourceSupport {
	private final Product product;

	public ProductResource(Product product, Long feedId) {
		this.product = product;

		add(linkTo(methodOn(ProductsController.class).createProduct(null, feedId, null)).withRel("feed-products"));
		add(linkTo(methodOn(ImagesController.class).upload(product.getId(), null)).withRel("upload-image"));
		add(linkTo(methodOn(ImagesController.class).images(product.getId())).withRel("product-images"));
	}

	public Product getProduct() {
		return product;
	}
}
