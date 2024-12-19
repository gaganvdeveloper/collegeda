package com.softgv.cda.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface StudentProfileService {

	ResponseEntity<?> saveStudentProfile(int uid, MultipartFile file);

	ResponseEntity<?> findAllStudentProfiles();

	ResponseEntity<?> findStudentProfileById(int id);

	ResponseEntity<?> assignDepartmentToStudentProfile(int uid, int did);

	ResponseEntity<?> setYearToStudentProfile(int id, String year);
	
}
