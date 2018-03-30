package com.trade.core.domain.image;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageBinaryRepository extends JpaRepository<ProductImageBinary, Long>, ProductImageBinaryRepositoryCustom {
}
