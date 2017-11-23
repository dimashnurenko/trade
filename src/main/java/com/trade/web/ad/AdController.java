package com.trade.web.ad;

import com.trade.domain.ad.Ad;
import com.trade.domain.ad.AdMapper;
import com.trade.domain.ad.AdService;
import com.trade.web.group.GroupController;
import com.trade.web.user.LoggedUser;
import com.trade.domain.chat.AdPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v1/ads")
public class AdController {

	private final AdService adService;
	private final AdMapper adMapper;
	private final AdPublisher adPublisher;

	@Autowired
	public AdController(AdService adService, AdMapper adMapper, AdPublisher adPublisher) {
		this.adService = adService;
		this.adMapper = adMapper;
		this.adPublisher = adPublisher;
	}

	@PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<AddResource> create(@RequestParam Long groupId,
	                                          @RequestBody AdDto dto,
	                                          LoggedUser loggedUser) throws URISyntaxException {
		Ad entity = adService.create(groupId, loggedUser.getId(), dto);
		Link selfLink = linkTo(methodOn(this.getClass()).findOne(groupId)).withSelfRel();
		return created(new URI(selfLink.getHref())).body(addLinks(adMapper.map(entity), groupId));
	}

	private AddResource addLinks(AddResource resource, Long groupId) {
		Long addId = resource.getAddId();
		resource.add(linkTo(methodOn(AdController.class).findOne(addId)).withSelfRel());
		resource.add(linkTo(methodOn(AdController.class).publish(addId)).withRel("publish"));
		resource.add(linkTo(methodOn(AdController.class).findByGroupId(groupId)).withRel("group_ads"));
		resource.add(linkTo(methodOn(GroupController.class).findOne(groupId)).withRel("group"));
		return resource;
	}

	@GetMapping(path = "/{adId}", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<AddResource> findOne(@PathVariable Long adId) {
		throw new UnsupportedOperationException("not supported yet");
	}

	@GetMapping(produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<AddResource> findByGroupId(@RequestParam Long groupId) {
		return ok().build();
	}

	@PostMapping(path = "/{adId}/publishing")
	public ResponseEntity publish(@PathVariable Long adId) {
		adPublisher.publish(adId);
		return ok().build();
	}
}
