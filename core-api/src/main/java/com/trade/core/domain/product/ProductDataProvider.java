package com.trade.core.domain.product;

import com.trade.core.domain.image.ProductImage;
import com.trade.core.domain.image.ProductImageRepo;
import org.springframework.stereotype.Component;

@Component
public class ProductDataProvider {
	private final ProductImageRepo imageRepo;

	public ProductDataProvider(ProductImageRepo imageRepo) {
		this.imageRepo = imageRepo;
	}

	public Long mainImageId(Long productId) {
		return imageRepo.findAllByProductId(productId)
		                .stream()
		                .filter(ProductImage::isMain)
		                .findFirst()
		                .map(ProductImage::getId)
		                .orElse(null);
	}
}
