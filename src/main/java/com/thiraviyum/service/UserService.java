package com.thiraviyum.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.thiraviyum.domain.User;

public interface UserService extends UserDetailsService {

	void register(User user);
}
