package com.softgv.cda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.softgv.cda.entity.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {

	@Query("select e from Enrollment e where e.student.id=:uid")
	List<Enrollment> findEnrollmentByUserId(int uid);
	
	@Query("select e from Enrollment e where e.course.faculty.id=:fid")
	List<Enrollment> findAllEnrollmentsByFacultyProfileId(int fid);

}
