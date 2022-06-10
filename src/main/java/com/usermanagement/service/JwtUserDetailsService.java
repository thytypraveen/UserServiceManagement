package com.usermanagement.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.usermanagement.dao.UserDao;
import com.usermanagement.data.User;
import com.usermanagement.data.UserDTO;

import ch.qos.logback.core.net.SyslogOutputStream;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
	}

	public com.usermanagement.data.User getuser(String name) {

		User user = userDao.findByUsername(name);
		return user;
	}

	public UserDTO save(UserDTO user) {
		User newUser = new User();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setEmail(user.getEmail());
		newUser.setContactNumber(user.getContactNumber());
		newUser = userDao.save(newUser);

		user.setId(newUser.getId());

		return user;

	}

	public Optional<User> getUser(UserDTO userDTO) {

		long idtobepass = 0L;

		Optional<User> user = userDao.findById(userDTO.getId());

		List<UserDTO> dtolist = new ArrayList<UserDTO>();

		dtolist.add(userDTO);

		List<Long> s = dtolist.stream().map(e -> e.getId()).collect(Collectors.toList());

		for (long x : s) {
			idtobepass = x;
		}
		User uu = userDao.findById(userDTO.getId()).orElse(new User());

		System.out.println("new user :" + uu);
		System.out.println("id t o be passed :" + idtobepass);

		System.out.println("new user's username and email :" + uu.getUsername() + "" + uu.getEmail());
		// using set

		Set<UserDTO> set = Stream.of(userDTO).collect(Collectors.toSet());

		Set<Long> ss = set.stream().map(e -> e.getId()).collect(Collectors.toSet());

		// Set<Long> ss1 = Stream.of(userDTO).map(e ->
		// e.getId()).collect(Collectors.toSet());

		Iterator<Long> usr = ss.iterator();

		long myids = 0L;

		while (usr.hasNext()) {

			myids = usr.next();

		}

		usr.forEachRemaining(e -> System.out.println("printing in for each :" + e));

		System.out.println("my ids in set:" + myids);

		return user;

	}

	public List<User> getAllUser() {
		
		System.out.println("reaching in getAlluser data ");
		
		List<User> dbuserlist = new ArrayList<>();
		
		userDao.findAll().forEach(dbuserlist::add);
		
		return dbuserlist;
		
	}

}