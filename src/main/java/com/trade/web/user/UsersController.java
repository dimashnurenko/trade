package com.trade.web.user;

import com.trade.domain.user.UserResource;
import com.trade.domain.user.UserService;

import com.trade.web.order.OrdersController;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/users")
public class UsersController {
    private final UserService userService;

    public UsersController(@Qualifier("userServiceWrapper") UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public UserResource findOne(@PathVariable(value = "id") Long id) {
        return new UserResource(userService.findOne(id));
    }

    @PostMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity createOne(@RequestBody UserDto dto) throws URISyntaxException {
        long userId = userService.createUser(dto);

        ResourceSupport resourceSupport = new ResourceSupport();
        resourceSupport.add(linkTo(methodOn(OrdersController.class).findOne(userId)).withSelfRel());

        return status(CREATED)
                .location(new URI(resourceSupport.getLink("self").getHref()))
                .build();
    }

    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity updateOne(@RequestBody UserDto dto) {
        userService.updateUser(dto);
        return status(NO_CONTENT).build();
    }
}
