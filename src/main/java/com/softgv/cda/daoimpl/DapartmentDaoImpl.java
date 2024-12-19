package com.softgv.cda.daoimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.softgv.cda.dao.DepartmentDao;
import com.softgv.cda.entity.Department;
import com.softgv.cda.repository.DepartmentRepository;

@Repository
public class DapartmentDaoImpl implements DepartmentDao {

	@Autowired
	private DepartmentRepository repository;

	@Override
	public Department saveDepartment(Department department) {
		return repository.save(department);
	}

	@Override
	public List<Department> findAllDepartments() {
		return repository.findAll();
	}

	@Override
	public Optional<Department> findDepartmentById(int id) {
		return repository.findById(id);
	}

	@Override
	public void deleteDepartmentById(int id) {
		repository.deleteById(id);
	}

}
