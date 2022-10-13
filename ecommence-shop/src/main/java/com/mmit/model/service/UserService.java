package com.mmit.model.service;


import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mmit.model.entities.User;
import com.mmit.model.entities.UserRole;
import com.mmit.model.repo.UserRepo;
@Service
public class UserService {
	
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UserRepo repo;

	public long countUser() {
		
		return repo.count();
	}

	public void save(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		repo.save(user);
	}
	
	public User saveUser(User user) {
		user.setRole(UserRole.admin);
		user.setPassword(encoder.encode(user.getPassword()));
		return repo.save(user);
	}

	public User profile(String email) {
		return repo.findUserByEmail(email);
	}

	public List<User> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	public User findById(Integer id) {
		// TODO Auto-generated method stub
		return repo.findById(id).orElse(new User());
	}

	public void deleteById(Integer id) {
		repo.deleteById(id);
		
	}

	

	

	
	

	
	  
	 
}
