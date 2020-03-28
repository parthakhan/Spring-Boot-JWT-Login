package com.partha.library.service.impl;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.partha.library.model.User;
import com.partha.library.repository.RoleRepository;
import com.partha.library.repository.UserRepository;
import com.partha.library.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;	

	/*
	 * @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;
	 */

	@Override
	public void save(User user) {
		user.setPassword(user.getPassword());
		user.setRole(user.getRole());
		userRepository.save(user);
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
}
