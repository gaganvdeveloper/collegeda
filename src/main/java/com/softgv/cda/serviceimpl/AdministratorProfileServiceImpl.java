package com.softgv.cda.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.softgv.cda.dao.AdministratorProfileDao;
import com.softgv.cda.entity.AdministratorProfile;
import com.softgv.cda.responsestructure.ResponseStructure;
import com.softgv.cda.service.AdministratorProfileService;
@Service
public class AdministratorProfileServiceImpl implements AdministratorProfileService{

	@Autowired
	private AdministratorProfileDao administratorProfileDao;
	
	
	@Override
	public ResponseEntity<?> saveAdministratorProfile(AdministratorProfile administratorProfile) {
		return null;
	}

	@Override
	public ResponseEntity<?> findAdministratorProfileById(int id) {
		Optional<AdministratorProfile> optinal = administratorProfileDao.findAdministratorProfileById(id);
//		if(optinal.isEmpty())
//			throw Exception();
		AdministratorProfile administratorProfile = optinal.get();
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value()).message("Administrator Found Successfully...").body(administratorProfile).build());
	}

}
