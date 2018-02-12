package com.trade.web.product;

import com.trade.domain.product.Product;
import com.trade.domain.product.ProductDto;
import com.trade.domain.product.ProductService;
import com.trade.web.user.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

@Controller
@RequestMapping("/api/v1/{feedId}/products")
public class ProductsController {
	private final ProductService productService;

	@Autowired
	public ProductsController(ProductService productService) {
		this.productService = productService;
	}

	@PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductResource> createProduct(@RequestBody ProductDto dto,
	                                                     @PathVariable Long feedId,
	                                                     LoggedUser loggedUser) {
		Product product = productService.createProduct(dto, feedId, loggedUser.getId());
		return ok(new ProductResource(product, feedId));
	}

	@GetMapping(produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductResource>> findByUserId(LoggedUser loggedUser) {
		List<Product> products = productService.findAllByUserId(loggedUser.getId());
		return ok(products.stream().map(it -> new ProductResource(it, loggedUser.getId())).collect(toList()));
	}
}
