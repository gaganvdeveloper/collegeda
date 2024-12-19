package com.softgv.cda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softgv.cda.responsestructure.ResponseStructure;
import com.softgv.cda.service.EnrollmentService;

@CrossOrigin
@RestController
@RequestMapping(value = "/enrollments")
public class EnrollmentController {

	@Autowired
	private EnrollmentService service;

	@PostMapping("/{uid}/{cid}")
	public ResponseEntity<?> saveEnrollment(@PathVariable int uid, @PathVariable int cid) {
		return service.saveEnrollment(uid, cid);
	}

	@GetMapping(value = "/user/{uid}")
	public ResponseEntity<?> findEnrollmemtByUserId(@PathVariable(name = "uid") int uid) {
		return service.findEnrollmentByUserId(uid);
	}

	@GetMapping(value = "/faculty/{fid}")
	public ResponseEntity<?> findAllEnrollmentsByFacultyProfileId(@PathVariable(name = "fid") int fid){
		return service.findAllEnrollmentsByFacultyProfileId(fid);
	}
	
	
}
