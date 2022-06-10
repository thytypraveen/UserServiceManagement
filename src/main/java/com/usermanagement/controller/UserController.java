package com.usermanagement.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.usermanagement.data.User;
import com.usermanagement.data.UserDTO;
import com.usermanagement.exceptionhandling.ResourceNotFoundException;
import com.usermanagement.service.JwtUserDetailsService;

@Controller
@RequestMapping("/user")
public class UserController {

	static Consumer<User> c2 = p -> System.out.println("username in capital : " + p.getUsername().toUpperCase());

	static Function<String, String> toUpperCaseLambda = String::toUpperCase;

	Supplier<User> userSupplier = User::new;

	private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@RequestMapping(value = "/getuserbyid", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> getUser(@RequestBody UserDTO userDTO) throws Exception {

		try {
			logger.info("reaching in getuserbyid");
			System.out.println("reaching in getuserbyid");

			System.out.println("supplier object : +" + userSupplier.get().getId());

			Optional<User> user = userDetailsService.getUser(userDTO);

			System.out.println("Applying function in getUser :" + toUpperCaseLambda.apply(user.get().getEmail()));

			if (user.isPresent() == false) {
				System.out.println("checking whether user is present or not : " + user.isPresent());

				throw new ResourceNotFoundException("User not found - Please check the id and try again ");
			}

			return new ResponseEntity<Optional<User>>(user, HttpStatus.OK);

		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException("User not found - Please check the id and try again ");
		}

	}

	// endpoint to find all users:

	@RequestMapping(value = "/getusers", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> getAllUser() throws Exception {

		try {
			logger.info("reaching in getalluser");
			System.out.println("reaching in getalluser");

			List<User> userList = userDetailsService.getAllUser();

			logger.info("before displaying in Consumer");
			userList.forEach(c2);

			Set<User> userSet = userList.stream().filter(
					e -> e.getUsername().equalsIgnoreCase("aaaa") && e.getEmail().equalsIgnoreCase("aaa@gmail.com"))
					.collect(Collectors.toSet());

			return new ResponseEntity<Set<User>>(userSet, HttpStatus.OK);

		} catch (Exception e) {
			throw new ResourceNotFoundException("Data not found - Please check again ");
		}

	}

}
