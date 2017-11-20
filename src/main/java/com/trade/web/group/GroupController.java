package com.trade.web.group;

import com.trade.domain.group.Group;
import com.trade.domain.group.GroupMapper;
import com.trade.domain.group.GroupService;
import com.trade.web.ad.AdController;
import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/api/v1/groups")
public class GroupController {

	private final GroupService service;
	private final GroupMapper mapper;

	@Autowired
	public GroupController(GroupService service, GroupMapper mapper) {
		this.service = service;
		this.mapper = mapper;
	}

	@PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
	public ResponseEntity<GroupResource> create(@RequestParam Long userId, @RequestBody GroupDto dto) throws URISyntaxException {
		Group group = service.create(userId, dto);
		GroupResource resource = addLinks(mapper.map(group));
		return created(new URI(resource.getLink("self").getHref())).body(resource);
	}

	private GroupResource addLinks(GroupResource resource) {
		resource.add(linkTo(methodOn(GroupController.class).findOne(resource.getGroupId())).withSelfRel());
		resource.add(linkTo(methodOn(AdController.class).findByGroupId(resource.getGroupId())).withRel("ads"));
		return resource;
	}

	@GetMapping(path = "/{groupId}", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<Group> findOne(@PathVariable Long groupId) {
		throw new UnsupportedOperationException("not supported yet");
	}
}
