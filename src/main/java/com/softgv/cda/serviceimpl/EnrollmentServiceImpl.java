package com.softgv.cda.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.softgv.cda.dao.CourseDao;
import com.softgv.cda.dao.EnrollmentDao;
import com.softgv.cda.dao.StudentProfileDao;
import com.softgv.cda.entity.Course;
import com.softgv.cda.entity.Enrollment;
import com.softgv.cda.entity.StudentProfile;
import com.softgv.cda.responsestructure.ResponseStructure;
import com.softgv.cda.service.EnrollmentService;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

	@Autowired
	private EnrollmentDao enrollmentDao;

	@Autowired
	private CourseDao courseDao;

	@Autowired
	private StudentProfileDao studentProfileDao;

	@Override
	public ResponseEntity<?> findEnrollmentByUserId(int uid) {
		List<Enrollment> enrollments = enrollmentDao.findEnrollmentByUserId(uid);
		if (enrollments.isEmpty())
			throw new RuntimeException("No Enrollments Found For The Specified User Id : " + uid);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("All Enrollments Found Successfully...").body(enrollments).build());
	}

	@Override
	public ResponseEntity<?> saveEnrollment(int uid, int cid) {
		Optional<StudentProfile> optional1 = studentProfileDao.findStudentProfileById(uid);
		if (optional1.isEmpty())
			throw new RuntimeException("Invalid Student Prfile Id : " + uid);
		Optional<Course> optional2 = courseDao.findCourseById(cid);
		if (optional2.isEmpty())
			throw new RuntimeException("Invalid Course Id : " + cid);
		StudentProfile studentProfile = optional1.get();
		Course course = optional2.get();
		Enrollment enrollment = Enrollment.builder().course(course).student(studentProfile).build();
		enrollment = enrollmentDao.saveEnrollment(enrollment);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value()).message("Enrollment Saved Successfully...").body(enrollment).build());
	}

	@Override
	public ResponseEntity<?> findAllEnrollmentsByFacultyProfileId(int fid) {
		List<Enrollment> enrollments = enrollmentDao.findAllEnrollmentsByFacultyProfileId(fid);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value()).message("Find All Enrollments of Faculty...").body(enrollments).build());
	}

}
