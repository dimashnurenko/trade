package com.trade.core.domain.image;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageMapper {

	ImageBinary toModel(ProductImageBinary entity);
}
