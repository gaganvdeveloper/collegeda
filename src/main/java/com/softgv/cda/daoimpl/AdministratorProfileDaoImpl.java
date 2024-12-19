package com.softgv.cda.daoimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.softgv.cda.dao.AdministratorProfileDao;
import com.softgv.cda.entity.AdministratorProfile;
import com.softgv.cda.repository.AdministratorProfileRepository;
@Repository
public class AdministratorProfileDaoImpl implements AdministratorProfileDao {

	@Autowired
	private AdministratorProfileRepository administratorProfileRepository;
	
	@Override
	public AdministratorProfile saveAdministratorProfile(AdministratorProfile administratorProfile) {
		return administratorProfileRepository.save(administratorProfile);
	}

	@Override
	public Optional<AdministratorProfile> findAdministratorProfileById(int id) {
		return administratorProfileRepository.findById(id);
	}
	
	

}
