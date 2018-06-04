package com.trade.web.tag

import com.trade.core.domain.tag.TagDto
import com.trade.core.domain.tag.TagMapper
import com.trade.core.domain.tag.TagResource
import com.trade.core.domain.tag.TagService
import com.trade.web.config.LoggedUser
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import springfox.documentation.annotations.ApiIgnore

@Controller
class TagController(private val tagService: TagService,
                    private val tagMapper: TagMapper) {

	@PostMapping(path = ["api/v1/tags"], consumes = [APPLICATION_JSON_VALUE], produces = [APPLICATION_JSON_VALUE])
	fun createTag(@RequestBody tag: TagDto): ResponseEntity<TagResource> {
		val createdTag = tagService.createTag(tag)
		return ok(TagResource(createdTag.name, createdTag.tagId))
	}

	@PostMapping(path = ["/api/v1/products/{productId}/tags/{tagId}"])
	fun assignTagToProduct(@PathVariable productId: Long,
	                       @PathVariable tagId: Long,
	                       @ApiIgnore
	                       loggedUser: LoggedUser): ResponseEntity<Any> {
		tagService.assignTagToProduct(productId, tagId)
		return ok().build()
	}

	@GetMapping(path = ["api/v1/tags"], produces = [APPLICATION_JSON_VALUE])
	fun searchTagsByName(@RequestParam name: String): ResponseEntity<List<TagResource?>> {
		return ok(tagService.searchAllByName(name).map { entity -> TagResource(entity.name, entity.tagId) })
	}
}