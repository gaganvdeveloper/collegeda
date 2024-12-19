package com.softgv.cda.daoimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.softgv.cda.dao.EnrollmentDao;
import com.softgv.cda.entity.Enrollment;
import com.softgv.cda.repository.EnrollmentRepository;

@Repository
public class EnrollmentDaoImpl implements EnrollmentDao {

	@Autowired
	private EnrollmentRepository repository;

	@Override
	public Enrollment saveEnrollment(Enrollment enrollment) {
		return repository.save(enrollment);
	}

	@Override
	public List<Enrollment> findEnrollmentByUserId(int uid) {
		return repository.findEnrollmentByUserId(uid);
	}

	@Override
	public List<Enrollment> findAllEnrollmentsByFacultyProfileId(int fid) {
		return repository.findAllEnrollmentsByFacultyProfileId(fid);
	}

}
