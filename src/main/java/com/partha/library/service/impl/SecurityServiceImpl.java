package com.partha.library.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.partha.library.service.SecurityService;

@Service("securityService")
public class SecurityServiceImpl implements SecurityService {

	@Override
	public String findLoggedInUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void autologin(String username, String password) {
		// TODO Auto-generated method stub
		
	}

	
	

}
