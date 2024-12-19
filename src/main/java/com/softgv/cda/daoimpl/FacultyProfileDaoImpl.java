package com.softgv.cda.daoimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.softgv.cda.dao.FacultyProfileDao;
import com.softgv.cda.entity.FacultyProfile;
import com.softgv.cda.repository.FacultyProfileRepository;
@Repository
public class FacultyProfileDaoImpl implements FacultyProfileDao{

	@Autowired
	private FacultyProfileRepository facultyProfileRepository ;
	
	
	@Override
	public FacultyProfile saveFacultyProfile(FacultyProfile facultyProfile) {
		return facultyProfileRepository.save(facultyProfile);
	}


	@Override
	public List<FacultyProfile> findAllFacultyProfile() {
		return facultyProfileRepository.findAll();
	}


	@Override
	public Optional<FacultyProfile> findFacultyProfileById(int id) {
		return facultyProfileRepository.findById(id);
	}

	
	
	
	
}
