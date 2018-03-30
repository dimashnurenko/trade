package com.trade.core.domain.image;

import java.util.List;

public interface ProductImageBinaryRepositoryCustom {

	List<Long> findIds(Long productId);
}
