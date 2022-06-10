package com.usermanagement.controller;

import java.util.Objects;

import org.apache.http.protocol.HTTP;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.usermanagement.service.JwtUserDetailsService;

import com.usermanagement.config.JwtTokenUtil;
//import com.usermanagement.data.DAOUser;
import com.usermanagement.data.JwtRequest;
import com.usermanagement.data.JwtResponse;
import com.usermanagement.data.User;
import com.usermanagement.data.UserDTO;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {

		logger.info("reaching in register");
		System.out.println("reaching in register user");

		UserDTO u = userDetailsService.save(user);

		return new ResponseEntity<UserDTO>(u, HttpStatus.OK);

		// return ResponseEntity.ok(userDetailsService.save(user));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

	// to get user information :

	@RequestMapping(value = "/getuser", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<User> getUser(@RequestBody UserDTO user) throws Exception {

		try {
			return ResponseEntity.ok(userDetailsService.getuser(user.getUsername()));
		} catch (UsernameNotFoundException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

}