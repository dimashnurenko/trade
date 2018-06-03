package com.trade.core.domain.image;

import com.trade.exception.CoreAPIException;
import com.trade.exception.ResourceNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.trade.exception.CoreExceptionReason.INCOMPATIBLE_DATA;
import static com.trade.exception.client.ApiExceptionDetails.exceptionDetails;

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
		productImage.setMain(true);
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
		ProductImage image = imageRepo.findById(imageId)
		                              .orElseThrow(()->new ResourceNotFoundException());
		ProductImageBinary imageBinary = imageBinaryRepo.findOneByProductImageId(image.getId());
		if (productId.equals(imageBinary.getProductId())) {
			return imageBinary.getData();
		}
		throw new CoreAPIException(INCOMPATIBLE_DATA, exceptionDetails("image.incompatible.with.product", new Object[]{imageId, productId}));
	}
}
