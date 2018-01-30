package com.trade.domain.image;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageBinary {
	private Long productImageId;
	private byte[] data;
}
