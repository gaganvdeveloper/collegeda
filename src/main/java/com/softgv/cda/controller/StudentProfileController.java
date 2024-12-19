package com.softgv.cda.controller;

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

import com.softgv.cda.service.StudentProfileService;

@CrossOrigin
@RestController
@RequestMapping(value = "/studentprofiles")
public class StudentProfileController {
	@Autowired
	StudentProfileService studentProfileService;

	@PostMapping
	public ResponseEntity<?> saveStudentProfile(@RequestParam int uid, @RequestParam MultipartFile file) {
		return studentProfileService.saveStudentProfile(uid, file);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findStudentProfileById(@PathVariable int id){
		return studentProfileService.findStudentProfileById(id);
	}
	
	@GetMapping
	public ResponseEntity<?> findAllStudentProfiles(){
		return studentProfileService.findAllStudentProfiles();
	}
	
	@PatchMapping(value = "/{uid}/{did}")
	public ResponseEntity<?> assignDepartmentToStudentProfile(@PathVariable(name = "uid") int uid,@PathVariable(name = "did") int did){
		return studentProfileService.assignDepartmentToStudentProfile(uid,did);
	}

	@PatchMapping(value = "/year/{uid}/{year}")
	public ResponseEntity<?> setYearToStudentProfile(@PathVariable(name = "uid") int id, @PathVariable(name = "year") String year){
		return studentProfileService.setYearToStudentProfile(id,year); 
	}
	
}
