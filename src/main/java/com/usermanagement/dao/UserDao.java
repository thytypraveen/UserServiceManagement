package com.usermanagement.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



import com.usermanagement.data.User;

@Repository
public interface UserDao extends CrudRepository<User, Long> {
	
	User findByUsername(String username);
	
	

	
}