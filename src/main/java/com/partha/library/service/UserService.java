package com.partha.library.service;

import com.partha.library.model.User;

public interface UserService {
	   void save(User user);

	    User findByUsername(String username);
	

}
