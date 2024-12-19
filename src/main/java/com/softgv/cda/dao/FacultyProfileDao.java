package com.softgv.cda.dao;

import java.util.List;
import java.util.Optional;

import com.softgv.cda.entity.FacultyProfile;

public interface FacultyProfileDao {

	FacultyProfile saveFacultyProfile(FacultyProfile facultyProfile);
	
	List<FacultyProfile> findAllFacultyProfile();

	Optional<FacultyProfile> findFacultyProfileById(int id);

}
