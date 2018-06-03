package com.trade.core.domain.tag

interface TagService {
	fun createTag(productId: Long, tag: Tag): Tag

	fun findProductsIdsByTagName(name: String): List<Long>

	fun findTagsByProductId(productId: Long): List<String>
}