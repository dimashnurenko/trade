package com.trade.core.domain.tag

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TagRepo : JpaRepository<TagEntity, Long> {
	private companion object {
		const val productsIdsByTagName = "select product_id from tag_product where name=?"
		const val tagsNamesByProductId = "select name from tag_product where product_id=?"
	}

	@Query(value = productsIdsByTagName, nativeQuery = true)
	fun findProductsIdsByTagName(name: String): List<Long>

	@Query(value = tagsNamesByProductId, nativeQuery = true)
	fun findTagsByProductId(productId: Long): List<String>
}