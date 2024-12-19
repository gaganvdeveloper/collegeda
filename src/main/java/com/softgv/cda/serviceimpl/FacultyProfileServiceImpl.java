package com.softgv.cda.serviceimpl;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.softgv.cda.dao.DepartmentDao;
import com.softgv.cda.dao.FacultyProfileDao;
import com.softgv.cda.dao.UserDao;
import com.softgv.cda.entity.Department;
import com.softgv.cda.entity.FacultyProfile;
import com.softgv.cda.entity.User;
import com.softgv.cda.exceptionclasses.UserNotFoundException;
import com.softgv.cda.responsestructure.ResponseStructure;
import com.softgv.cda.service.FacultyProfileService;

@Service
public class FacultyProfileServiceImpl implements FacultyProfileService {

	private static final String FOLDER_PATH = "C:\\Users\\gagan\\Documents\\My-React\\cda-react-app\\public\\images\\facultys\\";

	@Autowired
	private UserDao userDao;

	@Autowired
	private FacultyProfileDao facultyProfileDao;

	@Autowired
	private DepartmentDao departmentDao;
	
	
	@Override
	public ResponseEntity<?> saveFacultyProfile(int uid, MultipartFile file) {
		Optional<User> optional = userDao.findUserById(uid);
		if (optional.isEmpty())
			throw UserNotFoundException.builder().message("Inavlid User Id : " + uid).build();
		User user = optional.get();
		String photo = FOLDER_PATH + UUID.randomUUID() + file.getOriginalFilename();
		try {
			file.transferTo(new File(photo));
		} catch (IOException e) {
			e.printStackTrace();
		}
		FacultyProfile facultyProfile = FacultyProfile.builder().photo(photo).id(uid).user(user).build();

		facultyProfile = facultyProfileDao.saveFacultyProfile(facultyProfile);

		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Faculty Profile Saved Successfully...").body(facultyProfile).build());
	}

	@Override
	public ResponseEntity<?> findAllFacultyProfile() {
		List<FacultyProfile> facultyProfiles = facultyProfileDao.findAllFacultyProfile();
		if (facultyProfiles.isEmpty())
			throw new RuntimeException("No Faculty Profiles Present in Databse Table...");
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("All Faculty Profiles Found Succesfully...").body(facultyProfiles).build());
	}

	@Override
	public ResponseEntity<?> findFacultyProfileById(int id) {
		Optional<FacultyProfile> optional = facultyProfileDao.findFacultyProfileById(id);
		if (optional.isEmpty())
			throw new RuntimeException("Invalid Faculty Profile Id : " + id);
		FacultyProfile facultyProfile = optional.get();
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Faculty Found Successfully...").body(facultyProfile).build());
	}

	@Override
	public ResponseEntity<?> updatePhoto(int id, MultipartFile file) {
		Optional<FacultyProfile> optional = facultyProfileDao.findFacultyProfileById(id);
		if (optional.isEmpty())
			throw new RuntimeException("Invalid Faculty Profile Id : " + id);
		FacultyProfile facultyProfile = optional.get();
		String photo = FOLDER_PATH + UUID.randomUUID() + file.getOriginalFilename();
		try {
			file.transferTo(new File(photo));
		} catch (IOException e) {
			e.printStackTrace();
		}
		facultyProfile.setPhoto(photo);
		facultyProfile = facultyProfileDao.saveFacultyProfile(facultyProfile);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Photo Uploaded Successfully...").body(facultyProfile).build());
	}

	@Override
	public ResponseEntity<?> updateInfo(int id, String email, String phone, LocalTime officeHours) {
		Optional<FacultyProfile> optional = facultyProfileDao.findFacultyProfileById(id);
		if (optional.isEmpty())
			throw new RuntimeException("Invalid Faculty Profile Id : " + id);
		Optional<User> optional2 = userDao.findUserById(id);
		if (optional2.isEmpty())
			throw new RuntimeException("Invalid User Id : " + id);
		User user = optional2.get();
		FacultyProfile facultyProfile = optional.get();
		facultyProfile.setOfficeHours(officeHours);
		facultyProfile = facultyProfileDao.saveFacultyProfile(facultyProfile);
		user.setEmail(email);
		user.setPhone(phone);
		user = userDao.saveUser(user);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Faculty Information Updated Successfully...").body(facultyProfile).build());
	}

	@Override
	public ResponseEntity<?> assignDepartmentToFacultyProfile(int uid, int did) {
		Optional<Department> optional1 = departmentDao.findDepartmentById(did);
		if (optional1.isEmpty())
			throw new RuntimeException("Invalid Department Id : " + did);
		Optional<FacultyProfile> optional2 = facultyProfileDao.findFacultyProfileById(uid);
		if (optional2.isEmpty())
			throw new RuntimeException("Invalid Faculty Profile Id : " + uid);
		Department department = optional1.get();
		FacultyProfile  facultyProfile = optional2.get();
		facultyProfile.setDepartment(department);
		facultyProfile=facultyProfileDao.saveFacultyProfile(facultyProfile);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value()).message("Department Assigned Successfully To Faculty Profile").body(facultyProfile).build());
	}

}
