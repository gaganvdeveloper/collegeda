package com.softgv.cda.serviceimpl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.softgv.cda.dao.DepartmentDao;
import com.softgv.cda.dao.StudentProfileDao;
import com.softgv.cda.dao.UserDao;
import com.softgv.cda.entity.Department;
import com.softgv.cda.entity.StudentProfile;
import com.softgv.cda.entity.User;
import com.softgv.cda.exceptionclasses.UserNotFoundException;
import com.softgv.cda.responsestructure.ResponseStructure;
import com.softgv.cda.service.StudentProfileService;

@Service
public class StudentProfileServiceImpl implements StudentProfileService {
	private static final String FOLDER_PATH = "C:\\Users\\gagan\\Documents\\My-React\\cda-react-app\\public\\images\\students\\";

	@Autowired
	StudentProfileDao studentProfileDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private DepartmentDao departmentDao;

	@Override
	public ResponseEntity<?> saveStudentProfile(int uid, MultipartFile file) {
		Optional<User> optional = userDao.findUserById(uid);
		if (optional.isEmpty())
			throw UserNotFoundException.builder().message("Invalid User ID : " + uid).build();
		User user = optional.get();
		String photo = FOLDER_PATH + UUID.randomUUID() + file.getOriginalFilename();
		try {
			file.transferTo(new File(photo));
		} catch (IOException e) {
			e.printStackTrace();
		}
		StudentProfile studentProfile = StudentProfile.builder().id(uid).photo(photo).user(user).build();
		studentProfileDao.saveStudentProfile(studentProfile);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Student Profile Saved Successfully...").body(studentProfile).build());
	}

	@Override
	public ResponseEntity<?> findAllStudentProfiles() {

		List<StudentProfile> studentProfiles = studentProfileDao.findAllStudentProfiles();

		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("All Student Profiles Found Successfully...").body(studentProfiles).build());
	}

	@Override
	public ResponseEntity<?> findStudentProfileById(int id) {
		Optional<StudentProfile> optinal = studentProfileDao.findStudentProfileById(id);
		if (optinal.isEmpty())
			throw new RuntimeException();
		StudentProfile studentProfile = optinal.get();
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Student Profile Found Successfully...").body(studentProfile).build());
	}

	@Override
	public ResponseEntity<?> assignDepartmentToStudentProfile(int uid, int did) {
		Optional<Department> optional1 = departmentDao.findDepartmentById(did);
		if (optional1.isEmpty())
			throw new RuntimeException("Invalid Department Id : " + did);
		Optional<StudentProfile> optional2 = studentProfileDao.findStudentProfileById(uid);
		if (optional2.isEmpty())
			throw new RuntimeException("Invalid Student Profile Id : " + uid);
		Department department = optional1.get();
		StudentProfile studentProfile = optional2.get();
		studentProfile.setDepartment(department);
		studentProfile = studentProfileDao.saveStudentProfile(studentProfile);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Department Assigned To Student Profile Successfully...").body(studentProfile).build());
	}

	@Override
	public ResponseEntity<?> setYearToStudentProfile(int id, String year) {
		Optional<StudentProfile> optional = studentProfileDao.findStudentProfileById(id);
		if (optional.isEmpty())
			throw new RuntimeException("Invalid Student Profile Id : " + id);
		StudentProfile studentProfile = optional.get();
		studentProfile.setYear(year);
		studentProfile = studentProfileDao.saveStudentProfile(studentProfile);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value()).message("Year Updated Successfully...").body(studentProfile).build());
	}

}
