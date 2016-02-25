package com.thiraviyum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thiraviyum.dao.UserDao;
import com.thiraviyum.domain.User;
import com.thiraviyum.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findOne(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return user;
	}

}
