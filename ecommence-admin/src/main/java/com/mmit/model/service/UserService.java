package com.mmit.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmit.model.repo.UserRepo;

@Service
public class UserService {
	@Autowired
	private UserRepo repo;
}
