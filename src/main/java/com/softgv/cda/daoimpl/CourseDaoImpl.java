package com.softgv.cda.daoimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.softgv.cda.dao.CourseDao;
import com.softgv.cda.entity.Course;
import com.softgv.cda.repository.CourseRepository;

@Repository
public class CourseDaoImpl implements CourseDao {

	@Autowired
	private CourseRepository repository;

	@Override
	public Course saveCourse(Course course) {
		return repository.save(course);
	}

	@Override
	public List<Course> findAllCourses() {
		return repository.findAll();
	}

	@Override
	public Optional<Course> findCourseById(int id) {
		return repository.findById(id);
	}

	@Override
	public void deleteCourseById(int id) {
		repository.deleteById(id);
	}

}
