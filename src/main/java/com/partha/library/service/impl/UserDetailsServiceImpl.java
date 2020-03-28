package com.partha.library.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.partha.library.model.JwtUserDetail;
import com.partha.library.model.Role;
import com.partha.library.model.User;
import com.partha.library.repository.RoleRepository;
import com.partha.library.repository.UserRepository;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override

	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username); 
		// Role role = roleRepository.findByRole(user.getRole());

		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

		grantedAuthorities.add(new SimpleGrantedAuthority("Role_Admin"));

		return new JwtUserDetail(Long.valueOf(user.getUser_id()), user.getUsername(), user.getPassword(),
				"ROLE_ADMIN");
	}

	/*
	 * static List<JwtUserDetail> inMemoryUserList = new ArrayList<>();
	 * 
	 * static { inMemoryUserList.add(new JwtUserDetail(1L, "in28minutes",
	 * "$2a$10$3zHzb.Npv1hfZbLEU5qsdOju/tk2je6W6PnNnY.c1ujWPcZh4PL6e",
	 * "ROLE_USER_2")); inMemoryUserList.add(new JwtUserDetail(2L, "ranga",
	 * "$2a$10$IetbreuU5KihCkDB6/r1DOJO0VyU9lSiBcrMDT.biU7FOt2oqZDPm",
	 * "ROLE_USER_2"));
	 * 
	 * // $2a$10$IetbreuU5KihCkDB6/r1DOJO0VyU9lSiBcrMDT.biU7FOt2oqZDPm }
	 * 
	 * @Override public UserDetails loadUserByUsername(String username) throws
	 * UsernameNotFoundException { Optional<JwtUserDetail> findFirst =
	 * inMemoryUserList.stream() .filter(user ->
	 * user.getUsername().equals(username)).findFirst();
	 * 
	 * if (!findFirst.isPresent()) { throw new
	 * UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username)); }
	 * 
	 * return findFirst.get(); }
	 */

}
