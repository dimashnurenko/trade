package com.trade.web.ad;

import com.trade.domain.ad.AdEntity;
import com.trade.domain.ad.AdMapper;
import com.trade.domain.ad.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.created;

@RestController
@RequestMapping("/api/v1/groups/{groupId}/ads")
public class AdController {

	private final AdService adService;
	private final AdMapper adMapper;

	@Autowired
	public AdController(AdService adService, AdMapper adMapper) {
		this.adService = adService;
		this.adMapper = adMapper;
	}

	@PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<AddResource> create(@PathVariable Long groupId, @RequestBody AdDto dto) throws URISyntaxException {
		AdEntity entity = adService.create(groupId, dto);
		Link selfLink = linkTo(methodOn(this.getClass()).findOne(groupId, entity.getId())).withSelfRel();
		return created(new URI(selfLink.getHref())).body(addLinks(adMapper.map(entity), groupId));
	}

	private AddResource addLinks(AddResource resource, Long groupId) {
		resource.add(linkTo(methodOn(AdController.class).findOne(groupId, resource.getAddId())).withSelfRel());
//		resource.add(linkTo(methodOn(GroupController.class).findOne(groupId, resource.getAddId())).withSelfRel());
		return resource;
	}

	@GetMapping(path = "/{adId}", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<AddResource> findOne(@PathVariable Long groupId, @PathVariable Long adId) {
		throw new UnsupportedOperationException("not supported yet");
	}

	@GetMapping(produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<AddResource> findByGroupId(@PathVariable Long groupId) {
		return ResponseEntity.ok().build();
	}
}
