package com.trade.web.user;

import com.trade.domain.user.UserResource;
import com.trade.domain.user.UserService;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity updateOne(@RequestBody UserDto dto) {
        userService.updateUser(dto);
        return status(NO_CONTENT).build();
    }
}
