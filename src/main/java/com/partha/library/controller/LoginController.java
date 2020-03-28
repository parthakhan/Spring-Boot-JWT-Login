package com.partha.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.partha.library.JwtUtil;
import com.partha.library.model.AuthenticationRequest;
import com.partha.library.model.AuthenticationResponse;
import com.partha.library.model.User;
import com.partha.library.service.SecurityService;
import com.partha.library.service.UserService;
import com.partha.library.service.impl.UserDetailsServiceImpl;
import com.partha.library.validator.UserValidator;

@RestController
public class LoginController {
	/*
	 * @Autowired private UserService userService;
	 * 
	 * @Autowired private SecurityService securityService;
	 * 
	 * @Autowired private UserValidator userValidator;
	 * 
	 * @RequestMapping(value = "/registration", method = RequestMethod.GET) public
	 * String registration(Model model) { model.addAttribute("userForm", new
	 * User());
	 * 
	 * return "registration"; }
	 * 
	 * @RequestMapping(value = "/registration", method = RequestMethod.POST) public
	 * String registration(@ModelAttribute("userForm") User userForm, BindingResult
	 * bindingResult, Model model) { userValidator.validate(userForm,
	 * bindingResult);
	 * 
	 * if (bindingResult.hasErrors()) { return "registration"; }
	 * 
	 * userService.save(userForm);
	 * 
	 * securityService.autologin(userForm.getUsername(), userForm.getPassword());
	 * 
	 * return "redirect:/welcome"; }
	 * 
	 * @RequestMapping(value = "/login", method = RequestMethod.GET) public String
	 * login(Model model, String error, String logout) { if (error != null)
	 * model.addAttribute("error", "Your username and password are invalid.");
	 * 
	 * if (logout != null) model.addAttribute("message",
	 * "You have logged out successfully.");
	 * 
	 * return "login"; }
	 * 
	 * @RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
	 * public String welcome(Model model) { return "welcome"; }
	 */
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@RequestMapping({ "/hello" })
	public String firstPage() {
		return "login";
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwtUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}	
}
