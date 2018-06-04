package com.trade.core.domain.tag

import org.springframework.data.jpa.repository.JpaRepository

interface ProductTagMappingRepo : JpaRepository<ProductTagMapping, Long> {
	fun findAllByProductId(productId: Long): List<ProductTagMapping>

	fun findAllByTagId(tagId: Long): List<ProductTagMapping>

	fun findAllByTagIdAndCreatedBy(tagId: Long, createdBy: Long): List<ProductTagMapping>
}