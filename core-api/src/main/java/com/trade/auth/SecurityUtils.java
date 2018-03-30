package com.trade.auth;

import com.trade.auth.web.UserInfoDto;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;

import static java.util.stream.Collectors.toList;

public class SecurityUtils {

	public static UsernamePasswordAuthenticationToken createAuthentication(UserInfoDto user) {
		org.springframework.security.core.userdetails.User securityUser = new org.springframework.security.core.userdetails.User(user.getPhone(),
		                                                                                                                         "",
		                                                                                                                         getGrantedAuthorities(user));
		return new UsernamePasswordAuthenticationToken(securityUser, securityUser.getPassword(), securityUser.getAuthorities());
	}

	private static Collection<? extends GrantedAuthority> getGrantedAuthorities(UserInfoDto user) {
		return user.getRoles()
		           .stream()
		           .map(SimpleGrantedAuthority::new)
		           .collect(toList());
	}
}
