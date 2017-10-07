package com.trade.domain.user;

import com.trade.web.user.UsersController;
import com.trade.web.user.UserDto;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;


import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@Getter
public class UserResource extends ResourceSupport {
    private Long userId;
    private String name;
    private String phone;

    public UserResource(UserDto userDto) {
        this.userId = userDto.getId();
        this.name = userDto.getName();
        this.phone = userDto.getPhone();

       add(linkTo(methodOn(UsersController.class).findOne(userId)).withSelfRel());
    }
}
