package com.trade.core.domain.image;

import java.util.List;

public interface ImageStorage {

	void store(Long productId, byte[] imageBytes, String imageName);

	List<Long> findImagesIds(Long productId);

	byte[] findImage(Long productId, Long imageId);
}
