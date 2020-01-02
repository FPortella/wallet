package com.portella.wallet.service;

import java.util.Optional;

import com.portella.wallet.entity.User;

public interface UserService {

	User save(User u);
	
	Optional<User> findByEmail(String email);
}
