package com.trade.core.domain.tag

import org.springframework.stereotype.Component

@Component
class TagServiceImpl(private val tagRepo: TagRepo,
                     private val tagMapper: TagMapper) : TagService {


	override fun createTag(productId: Long, tag: Tag): Tag {
		val tagEntity = tagRepo.save(TagEntity(null, tag.name, productId))
		return tagMapper.toModel(tagEntity)
	}

	override fun findProductsIdsByTagName(name: String): List<Long> {
		return ArrayList()
	}

	override fun findTagsByProductId(productId: Long): List<String> {
		return ArrayList()
	}
}