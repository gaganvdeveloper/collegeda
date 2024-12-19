package com.softgv.cda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softgv.cda.entity.Department;
import com.softgv.cda.service.DepartmentService;
@CrossOrigin
@RestController
@RequestMapping(value = "/departments")
public class DepartmentController {
	
	@Autowired
	private DepartmentService service;
	
	@PostMapping
	public ResponseEntity<?> saveDepartment(@RequestBody Department department){
		return service.saveDepartment(department);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> findDepartmentById(@PathVariable int id){
		return service.findDepartmentById(id);
	}
	
	
	@GetMapping
	public ResponseEntity<?> findAllDepartments(){
		return service.findAllDepartments();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteDepartmentById(@PathVariable int id){
		return service.deleteDepartmentById(id);
	}
}
