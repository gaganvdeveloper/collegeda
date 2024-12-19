package com.softgv.cda.dao;

import java.util.List;
import java.util.Optional;

import com.softgv.cda.entity.Course;

public interface CourseDao {

	Course saveCourse(Course course);

	List<Course> findAllCourses();

	Optional<Course> findCourseById(int id);

	void deleteCourseById(int id);

}
