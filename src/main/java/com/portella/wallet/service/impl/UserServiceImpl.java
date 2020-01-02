package com.portella.wallet.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portella.wallet.entity.User;
import com.portella.wallet.repository.UserRepository;
import com.portella.wallet.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository repository;
	
	@Override
	public User save(User u) {
		return repository.save(u);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return repository.findByEmailEquals(email);
	}

	
	
}
