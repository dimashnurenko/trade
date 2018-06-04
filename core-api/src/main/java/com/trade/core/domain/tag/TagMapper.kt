package com.trade.core.domain.tag

import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface TagMapper {

	@Mapping(source = "id", target = "tagId")
	fun toModel(tag: TagEntity): Tag

	fun toResource(tag: Tag): TagResource
}