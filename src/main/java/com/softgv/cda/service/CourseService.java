package com.softgv.cda.service;

import org.springframework.http.ResponseEntity;

import com.softgv.cda.entity.Course;

public interface CourseService {

	ResponseEntity<?> saveCourse(Course course);

	ResponseEntity<?> findAllCourses();

	ResponseEntity<?> findCourseById(int id);

	ResponseEntity<?> deleteCourseById(int id);

	ResponseEntity<?> assignFacultyToCourse(int id, int fid);

}
