package com.softgv.cda.serviceimpl;

import java.time.LocalTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.softgv.cda.dao.AdministratorProfileDao;
import com.softgv.cda.dao.FacultyProfileDao;
import com.softgv.cda.dao.StudentProfileDao;
import com.softgv.cda.dao.UserDao;
import com.softgv.cda.entity.AdministratorProfile;
import com.softgv.cda.entity.FacultyProfile;
import com.softgv.cda.entity.StudentProfile;
import com.softgv.cda.entity.User;
import com.softgv.cda.exceptionclasses.UserNotFoundException;
import com.softgv.cda.responsestructure.ResponseStructure;
import com.softgv.cda.service.UserService;
import com.softgv.cda.util.AuthUser;
import com.softgv.cda.util.Helper;
import com.softgv.cda.util.MyUtil;
import com.softgv.cda.util.Role;
import com.softgv.cda.util.UserStatus;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private Helper helper;

	
	@Autowired
	private JavaMailSender javaMailSender;
	
	
	
	
	
	@Autowired
	private UserDao userDao;

	@Autowired
	private StudentProfileDao studentProfileDao;

	@Autowired
	private FacultyProfileDao facultyProfileDao;

	@Autowired
	private AdministratorProfileDao administratorProfileDao;

	public ResponseEntity<?> findByUsernameAndPassword(AuthUser authUser) {
		Optional<User> optional = userDao.findByUsernameAndPassword(authUser.getUsername(), authUser.getPassword());
		if (optional.isEmpty())
			throw UserNotFoundException.builder().message("Invalid Credentials... Invalid Username or Password...")
					.build();
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("User Verified Successfully...").body(optional.get()).build());
	}

	@Override
	public ResponseEntity<?> saveUser(User user) {
		String photo = "C:\\Users\\gagan\\Documents\\My-React\\cda-react-app\\public\\images\\userprofile.jpg";
		
		user.setOtp(MyUtil.getOTP());
		
		user = userDao.saveUser(user);
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.addTo(user.getEmail());
			mimeMessageHelper.setSubject("Account Created");
			
			
			String html = "<html>\r\n"
					+ "  <body style=\"background-color: #e0f7fa; font-family: Arial, sans-serif; margin: 0; padding: 0;\">\r\n"
					+ "    <div style=\"width: 100%; display: flex; justify-content: center; padding: 20px;\">\r\n"
					+ "      <div style=\"background-color: #ffffff; border-radius: 15px; padding: 40px; box-shadow: 0px 8px 16px rgba(0, 0, 0, 0.1); max-width: 600px; text-align: center; border: 1px solid #ccc;\">\r\n"
					+ "        <h1 style=\"color: #00796b; font-size: 28px; font-weight: bold; margin-bottom: 20px;\">Welcome to Your CDA Account!</h1>\r\n"
					+ "        <p style=\"font-size: 18px; color: #333333; line-height: 1.6;\">Hello <span style=\"font-weight: bold; color: #00796b;\">" + user.getName() + "</span>,</p>\r\n"
					+ "        <p style=\"font-size: 18px; color: #333333; line-height: 1.6;\">Your CDA Account has been created successfully. We are excited to have you with us!</p>\r\n"
					+ "        \r\n"
					+ "        <hr style=\"border: 1px solid #00796b; width: 50%; margin: 20px auto;\">\r\n"
					+ "        \r\n"
					+ "        <p style=\"font-size: 20px; color: #00796b; font-weight: bold;\">Your OTP:</p>\r\n"
					+ "        <h2 style=\"color: #ff5722; font-size: 32px; font-weight: bold; letter-spacing: 2px; margin-bottom: 20px;\">" + user.getOtp() + "</h2>\r\n"
					+ "        \r\n"
					+ "        <p style=\"font-size: 16px; color: #333333; line-height: 1.6;\">Please use this OTP to complete the verification process. It is valid for the next 10 minutes.</p>\r\n"
					+ "        \r\n"
					+ "        <div style=\"margin-top: 30px;\">\r\n"
					+ "          <a href=\"#\" style=\"background-color: #00796b; color: #ffffff; padding: 12px 30px; text-decoration: none; border-radius: 30px; font-size: 18px; font-weight: bold;\">Go to Dashboard</a>\r\n"
					+ "        </div>\r\n"
					+ "        \r\n"
					+ "        <div style=\"margin-top: 40px; font-size: 14px; color: #777777;\">\r\n"
					+ "          <p>Thank you for choosing our service. If you did not create this account, please contact us immediately.</p>\r\n"
					+ "        </div>\r\n"
					+ "      </div>\r\n"
					+ "    </div>\r\n"
					+ "  </body>\r\n"
					+ "</html>\r\n"
					+ "";
			
			
			mimeMessageHelper.setText(html,true);
			javaMailSender.send(mimeMessage);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("User Saved Successfully...").body(user).build());
	}

	@Override
	public ResponseEntity<?> findAllUsers() {
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("All Users Found Successfully...").body(userDao.findAllUsers()).build());
	}

	@Override
	public ResponseEntity<?> findUserById(int id) {
		Optional<User> optional = userDao.findUserById(id);
		if (optional.isEmpty())
			throw UserNotFoundException.builder().message("Invalid User Id : " + id).build();
		User user = optional.get();
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("User Found Successfully...").body(user).build());
	}

	@Override
	public ResponseEntity<?> verifyOTP(int id, int otp) {
		Optional<User> optional = userDao.findUserById(id);
		if(optional.isEmpty())
			throw new RuntimeException("Invalid User ID unable to verify the OTP");
		User user = optional.get();
		if(otp!=user.getOtp())
			throw new RuntimeException("Invalid OTP unable to verify the OTP");
		user.setStatus(UserStatus.ACTIVE);
		user = userDao.saveUser(user);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value()).message("OTP Verified Successfully").body(user).build());
	}

	@Override
	public ResponseEntity<?> findUserByEmail(String email) {
		Optional<User> optional =  userDao.findUserByEmail(email);
		if(optional.isEmpty())
			throw new RuntimeException("Invalid Email id No Matching User Found");
		User user = optional.get();
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value()).message("User Found Successfully").body(user).build());
	}
	
	
}
