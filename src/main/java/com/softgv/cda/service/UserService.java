package com.softgv.cda.service;

import org.springframework.http.ResponseEntity;

import com.softgv.cda.entity.User;
import com.softgv.cda.util.AuthUser;

public interface UserService {

	ResponseEntity<?> findByUsernameAndPassword(AuthUser authUser);

	ResponseEntity<?> saveUser(User user);

	ResponseEntity<?> findAllUsers();

	ResponseEntity<?> findUserById(int id);

	ResponseEntity<?> verifyOTP(int id, int otp);

	ResponseEntity<?> findUserByEmail(String email);
	
}
