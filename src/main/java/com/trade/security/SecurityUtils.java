package com.trade.security;

import com.trade.domain.user.Role;
import com.trade.domain.user.User;
import com.trade.domain.user.UserRole;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;

import static java.util.stream.Collectors.toList;

public class SecurityUtils {

	public static UsernamePasswordAuthenticationToken createAuthentication(User user) {
		org.springframework.security.core.userdetails.User securityUser = new org.springframework.security.core.userdetails.User(user.getName(),
		                                                                                                                         user.getPassword(),
		                                                                                                                         getGrantedAuthorities(user));
		return new UsernamePasswordAuthenticationToken(securityUser, securityUser.getPassword(), securityUser.getAuthorities());
	}

	private static Collection<? extends GrantedAuthority> getGrantedAuthorities(User user) {
		return user.getRoles()
		           .stream()
		           .map(UserRole::getRole)
		           .map(Role::toString)
		           .map(it -> new SimpleGrantedAuthority("ROLE_" + it))
		           .collect(toList());
	}
}
