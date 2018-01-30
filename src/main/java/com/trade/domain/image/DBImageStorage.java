package com.trade.domain.image;

import com.trade.common.exception.ServerException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.lang.String.format;

@Component
public class DBImageStorage implements ImageStorage {
	private final ProductImageRepo imageRepo;
	private final ProductImageBinaryRepository imageBinaryRepo;

	public DBImageStorage(ProductImageRepo imageRepo, ProductImageBinaryRepository imageBinaryRepo) {
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
		imageBinary.setProductId(productId);
		imageBinary.setData(imageBytes);

		imageBinaryRepo.save(imageBinary);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Long> findImagesIds(Long productId) {
		return imageBinaryRepo.findIds(productId);
	}

	@Override
	@Transactional(readOnly = true)
	public byte[] findImage(Long productId, Long imageId) {
		ProductImageBinary imageBinary = imageBinaryRepo.findOne(imageId);
		if (productId.equals(imageBinary.getProductId())) {
			return imageBinary.getData();
		}
		throw new ServerException(format("The image %s doesn't belong to product %s", imageId, productId));
	}
}
