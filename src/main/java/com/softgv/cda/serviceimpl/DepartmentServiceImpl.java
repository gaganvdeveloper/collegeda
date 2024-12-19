package com.softgv.cda.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.softgv.cda.dao.DepartmentDao;
import com.softgv.cda.entity.Department;
import com.softgv.cda.responsestructure.ResponseStructure;
import com.softgv.cda.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentDao departmentDao;

	@Override
	public ResponseEntity<?> saveDepartment(Department department) {
		department = departmentDao.saveDepartment(department);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Department Saved Successfully...").body(department).build());
	}

	@Override
	public ResponseEntity<?> findAllDepartments() {
		List<Department> departments = departmentDao.findAllDepartments();
		if (departments.isEmpty())
			throw new RuntimeException("No Departments Found In Database Table...");
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("All Departments Fetched Successfully...").body(departments).build());
	}

	@Override
	public ResponseEntity<?> findDepartmentById(int id) {
		Optional<Department> optional = departmentDao.findDepartmentById(id);
		if (optional.isEmpty())
			throw new RuntimeException("Invalid Department Id : " + id);
		Department department = optional.get();
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Department Found Successfully...").body(department).build());
	}

	@Override
	public ResponseEntity<?> deleteDepartmentById(int id) {
		Optional<Department> optional = departmentDao.findDepartmentById(id);
		if (optional.isEmpty())
			throw new RuntimeException("Invalid Department Id : " + id);
		departmentDao.deleteDepartmentById(id);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Department Deleted Successfully").body("Department Deleted Successfully").build());
	}

}
