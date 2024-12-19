package com.softgv.cda.dao;

import java.util.List;

import com.softgv.cda.entity.Enrollment;

public interface EnrollmentDao {

	List<Enrollment> findEnrollmentByUserId(int uid);

	Enrollment saveEnrollment(Enrollment enrollment);

	List<Enrollment> findAllEnrollmentsByFacultyProfileId(int fid);

}
