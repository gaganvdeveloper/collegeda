package com.softgv.cda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softgv.cda.entity.AdministratorProfile;
import com.softgv.cda.service.AdministratorProfileService;

@CrossOrigin
@RestController
@RequestMapping(value = "/administratorprofiles")
public class AdministratorProfileController {

	@Autowired
	private AdministratorProfileService administratorProfileService;

	@PostMapping
	public ResponseEntity<?> saveAdministratorProfile(@RequestBody AdministratorProfile administratorProfile) {
		return administratorProfileService.saveAdministratorProfile(administratorProfile);
	}
	
	

}
