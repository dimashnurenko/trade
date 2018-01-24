package com.trade.domain.image;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DBImageStorage implements ImageStorage {
	private final ProductImageRepo imageRepo;
	private final ProductImageBinaryRepo imageBinaryRepo;

	public DBImageStorage(ProductImageRepo imageRepo, ProductImageBinaryRepo imageBinaryRepo) {
		this.imageRepo = imageRepo;
		this.imageBinaryRepo = imageBinaryRepo;
	}

	@Override
	@Transactional
	public void store(Long productId, byte[] imageBytes, String imageName) {
		ProductImage productImage = new ProductImage();
		productImage.setProductId(productId);
//		productImage.setUrl(); todo upload to s3

		imageRepo.save(productImage);

		ProductImageBinary imageBinary = new ProductImageBinary();
		imageBinary.setProductImageId(productImage.getId());
		imageBinary.setData(imageBytes);

		imageBinaryRepo.save(imageBinary);
	}
}
