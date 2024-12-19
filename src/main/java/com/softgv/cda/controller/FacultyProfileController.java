package com.softgv.cda.controller;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.softgv.cda.service.FacultyProfileService;

@CrossOrigin
@RestController
@RequestMapping(value = "/facultyprofiles")
public class FacultyProfileController {

	@Autowired
	private FacultyProfileService facultyProfileService;

	@PostMapping
	public ResponseEntity<?> saveFacultyProfile(@RequestParam int uid, @RequestParam MultipartFile file) {
		return facultyProfileService.saveFacultyProfile(uid, file);
	}

	@PatchMapping("/update")
	public ResponseEntity<?> updateInfo(@RequestParam int id, @RequestParam String email, @RequestParam String phone,
			@RequestParam LocalTime officeHours) {
		return facultyProfileService.updateInfo(id, email, phone, officeHours);
	}

	@PatchMapping(value = "/{uid}/{did}")
	public ResponseEntity<?> assignDepartmentToFacultyProfile(@PathVariable(name = "uid") int uid,
			@PathVariable(name = "did") int did) {
		return facultyProfileService.assignDepartmentToFacultyProfile(uid, did);
	}

	@PatchMapping
	public ResponseEntity<?> updatePhoto(@RequestParam int id, @RequestParam MultipartFile file) {
		return facultyProfileService.updatePhoto(id, file);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findFacultyProfileById(@PathVariable int id) {
		return facultyProfileService.findFacultyProfileById(id);
	}

	@GetMapping
	public ResponseEntity<?> findAllFacultyProfile() {
		return facultyProfileService.findAllFacultyProfile();
	}

}
