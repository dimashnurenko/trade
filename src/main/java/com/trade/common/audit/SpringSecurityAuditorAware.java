package com.trade.common.audit;

import com.trade.domain.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class SpringSecurityAuditorAware implements AuditorAware<Long> {
	private final UserRepo repo;

	@Autowired
	public SpringSecurityAuditorAware(UserRepo repo) {
		this.repo = repo;
	}

	public Long getCurrentAuditor() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null || !authentication.isAuthenticated()) {
			return null;
		}

		String phone = ((User) authentication.getPrincipal()).getUsername();

		com.trade.domain.user.User user = repo.findFirstByPhone(phone);
		return user == null ? null : user.getId();
	}
}
