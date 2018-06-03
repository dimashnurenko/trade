package com.trade.core.domain.tag

import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface TagMapper {

	fun toModel(tag: TagEntity): Tag
}