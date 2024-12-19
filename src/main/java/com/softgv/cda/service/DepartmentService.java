package com.softgv.cda.service;

import org.springframework.http.ResponseEntity;

import com.softgv.cda.entity.Department;

public interface DepartmentService {

	ResponseEntity<?> saveDepartment(Department department);

	ResponseEntity<?> findAllDepartments();

	ResponseEntity<?> findDepartmentById(int id);

	ResponseEntity<?> deleteDepartmentById(int id);

}
