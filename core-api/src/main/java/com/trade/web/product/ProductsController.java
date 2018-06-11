package com.trade.web.product;

import com.trade.core.domain.product.Product;
import com.trade.core.domain.product.ProductDto;
import com.trade.core.domain.product.ProductService;
import com.trade.web.config.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

@Controller
@RequestMapping("/api/v1/products")
public class ProductsController {
	private final ProductService productService;

	@Autowired
	public ProductsController(ProductService productService) {
		this.productService = productService;
	}

	@PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductResource> createProduct(@RequestBody ProductDto dto,
	                                                     @ApiIgnore LoggedUser loggedUser) {
		Product product = productService.createProduct(dto, loggedUser.getId());
		return ok(new ProductResource(product));
	}

	@GetMapping(produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<ProductResource>> findByUserId(@ApiIgnore LoggedUser loggedUser, Pageable pageable) {
		Page<Product> products = productService.findAllByUserId(loggedUser.getId(), pageable);
		return ok(products.map(ProductResource::new));
	}

	@GetMapping(path = "/search", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductResource>> findAllByTagName(@RequestParam String tagName,
	                                                              @ApiIgnore LoggedUser loggedUser) {
		List<Product> products = productService.findAllByTagName(tagName, loggedUser.getId());
		return ok(products.stream().map(ProductResource::new).collect(toList()));
	}
}
