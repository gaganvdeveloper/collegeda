package com.softgv.cda.service;

import java.time.LocalTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FacultyProfileService {

	ResponseEntity<?> saveFacultyProfile(int uid, MultipartFile file);

	ResponseEntity<?> findAllFacultyProfile();

	ResponseEntity<?> findFacultyProfileById(int id);

	ResponseEntity<?> updatePhoto(int id, MultipartFile file);

	ResponseEntity<?> updateInfo(int id, String email, String phone, LocalTime officeHours);

	ResponseEntity<?> assignDepartmentToFacultyProfile(int uid, int did);
}
