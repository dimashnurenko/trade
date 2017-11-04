package com.trade.web.group;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;

@Getter
@Setter
public class GroupResource extends ResourceSupport {
	private Long groupId;
	private Long userId;
	private String name;
}
