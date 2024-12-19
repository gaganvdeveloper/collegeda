package com.softgv.cda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.softgv.cda.entity.User;
import com.softgv.cda.service.UserService;
import com.softgv.cda.util.AuthUser;

@CrossOrigin
@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	UserService service;

	@PostMapping(value = "/login")
	public ResponseEntity<?> findByUsernameAndPassword(@RequestBody AuthUser authUser) {
		return service.findByUsernameAndPassword(authUser);
	}

	@PostMapping
	public ResponseEntity<?> saveUser(@RequestBody User user) {
		return service.saveUser(user);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> findUserById(@PathVariable int id) {
		return service.findUserById(id);
	}

	@GetMapping
	public ResponseEntity<?> findAllUsers() {
		return service.findAllUsers();
	}

	@PatchMapping("/{id}/otp/{otp}")
	public ResponseEntity<?> verifyOTP(@PathVariable int id, @PathVariable int otp){
		return service.verifyOTP(id,otp);
	}
	
	@GetMapping("/email/{email}")
	public ResponseEntity<?> findUserByEmail(@PathVariable String email){
		return service.findUserByEmail(email);
	}
	
	
}
