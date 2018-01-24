package com.trade.domain.image;

public interface ImageStorage {

	void store(Long productId, byte[] imageBytes, String imageName);
}
