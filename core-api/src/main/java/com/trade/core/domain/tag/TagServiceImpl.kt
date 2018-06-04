package com.trade.core.domain.tag

import com.trade.exception.ResourceNotFoundException
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
open class TagServiceImpl(private val tagRepo: TagRepo,
                          private val tagMappingRepo: ProductTagMappingRepo,
                          private val tagMapper: TagMapper) : TagService {

	@Transactional
	override fun createTag(tag: TagDto): Tag {
		val foundTag = tagRepo.findOneByName(tag.name)
		if (foundTag != null) {
			return tagMapper.toModel(foundTag)
		}

		val tagEntity = tagRepo.save(TagEntity(null, tag.name))
		return tagMapper.toModel(tagEntity)
	}

	@Transactional
	override fun assignTagToProduct(productId: Long, tagId: Long) {
		val productTagMapping = ProductTagMapping(null, tagId, productId)
		tagMappingRepo.save(productTagMapping)
	}

	@Transactional(readOnly = true)
	override fun findTagByName(name: String): Tag {
		val tag = tagRepo.findOneByName(name) ?: throw ResourceNotFoundException("tag", "")
		return tagMapper.toModel(tag)
	}

	@Transactional(readOnly = true)
	override fun findProductsIdsByTagId(tagId: Long): List<Long?> {
		return tagMappingRepo.findAllByTagId(tagId).map(ProductTagMapping::productId)
	}

	@Transactional(readOnly = true)
	override fun findProductsIdsByTagName(tagName: String, userId: Long): List<Long?> {
		val tag = tagRepo.findOneByName(tagName) ?: throw ResourceNotFoundException("tag", tagName)
		return tagMappingRepo.findAllByTagIdAndCreatedBy(tag.id!!, userId).map(ProductTagMapping::productId)
	}

	@Transactional(readOnly = true)
	override fun searchAllByName(name: String): List<Tag> {
		return tagRepo.findAllWhereNameLike(name).map { tag -> tagMapper.toModel(tag!!) }
	}
}