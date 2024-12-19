package com.softgv.cda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softgv.cda.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer>{
	
}
