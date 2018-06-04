package com.trade.core.domain.tag

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TagRepo : JpaRepository<TagEntity, Long> {

	fun findOneByName(name: String): TagEntity?

	@Query("select t from TagEntity t where t.name like %:name%")
	fun findAllWhereNameLike(name: String): List<TagEntity?>
}