package com.trade.web.follower;

import com.trade.domain.follower.FollowersService;
import com.trade.web.user.LoggedUser;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.http.ResponseEntity.ok;

@Controller
@RequestMapping("/api/v1/followers")
public class FollowersController {

	private final FollowersService followersService;

	public FollowersController(FollowersService followersService) {
		this.followersService = followersService;
	}

	@PostMapping
	public ResponseEntity follow(LoggedUser loggedUser, @RequestParam Long followedOn) {
		followersService.follow(loggedUser.getId(), followedOn);
		return ok().build();
	}

	@DeleteMapping
	public ResponseEntity unFollow(LoggedUser loggedUser, @RequestParam Long followedOn) {
		followersService.unFollow(loggedUser.getId(), followedOn);
		return ok().build();
	}
}
