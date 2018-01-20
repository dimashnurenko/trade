package com.trade.web.product;

import com.trade.domain.product.Product;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class ProductResource extends ResourceSupport {
	private final Product product;

	public ProductResource(Product product, Long feedId) {
		this.product = product;

		add(linkTo(methodOn(ProductsController.class).createProduct(null, feedId, null)).withRel("feed_products"));
	}

	public Product getProduct() {
		return product;
	}
}
