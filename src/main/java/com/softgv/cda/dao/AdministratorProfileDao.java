package com.softgv.cda.dao;

import java.util.Optional;

import com.softgv.cda.entity.AdministratorProfile;

public interface AdministratorProfileDao {
	
	
	AdministratorProfile saveAdministratorProfile(AdministratorProfile administratorProfile);
	
	Optional<AdministratorProfile> findAdministratorProfileById(int id);
	
}
