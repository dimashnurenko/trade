package com.trade.core.domain.product;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

	Product toModel(ProductEntity entity);

	ProductEntity toEntity(Product product);

	ProductEntity toEntity(ProductDto dto);
}
