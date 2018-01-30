package com.trade.domain.image;

import java.util.List;

public interface ProductImageBinaryRepositoryCustom {

	List<Long> findIds(Long productId);
}
