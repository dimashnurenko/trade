package com.trade.core.common.audit;

import com.trade.core.domain.user.UserRepo;
import com.trade.core.domain.user.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static java.util.Optional.ofNullable;

@Component
public class SpringSecurityAuditorAware implements AuditorAware<Long> {
	private final UserRepo repo;

	@Autowired
	public SpringSecurityAuditorAware(UserRepo repo) {
		this.repo = repo;
	}

	public Optional<Long> getCurrentAuditor() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null || !authentication.isAuthenticated()) {
			return Optional.empty();
		}

		String phone = ((User) authentication.getPrincipal()).getUsername();

		return ofNullable(repo.findFirstByPhone(phone)).map(UserEntity::getId);
	}
}
