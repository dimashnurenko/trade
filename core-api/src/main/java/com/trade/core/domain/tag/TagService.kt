package com.trade.core.domain.tag

interface TagService {
	fun createTag(tag: TagDto): Tag

	fun findTagByName(name: String): Tag

	fun searchAllByName(name: String): List<Tag>

	fun assignTagToProduct(productId: Long, tagId: Long)

	fun findProductsIdsByTagId(tagId: Long): List<Long?>

	fun findProductsIdsByTagName(tagName: String, userId: Long): List<Long?>
}