package com.trade.security;

import com.trade.domain.user.Role;
import com.trade.domain.user.UserEntity;
import com.trade.domain.user.UserRepo;
import com.trade.domain.user.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static java.util.stream.Collectors.toList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	private final UserRepo userRepo;

	@Autowired
	public UserDetailsServiceImpl(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
		UserEntity user = userRepo.findFirstByPhone(phone);
		if (user == null) {
			throw new UsernameNotFoundException("User doesn't exists");
		}
		return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), getGrantedAuthorities(user));
	}

	private Collection<? extends GrantedAuthority> getGrantedAuthorities(UserEntity user) {
		return user.getRoles()
		           .stream()
		           .map(UserRole::getRole)
		           .map(Role::toString)
		           .map(it -> new SimpleGrantedAuthority("ROLE_" + it))
		           .collect(toList());
	}
}
