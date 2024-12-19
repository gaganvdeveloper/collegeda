package com.softgv.cda.service;

import org.springframework.http.ResponseEntity;

import com.softgv.cda.entity.AdministratorProfile;

public interface AdministratorProfileService {
	
	ResponseEntity<?> saveAdministratorProfile(AdministratorProfile administratorProfile);
	
	ResponseEntity<?> findAdministratorProfileById(int id);
	
}
