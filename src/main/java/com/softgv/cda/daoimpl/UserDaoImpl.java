package com.softgv.cda.daoimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.softgv.cda.dao.UserDao;
import com.softgv.cda.entity.User;
import com.softgv.cda.repository.UserRepository;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	UserRepository repository;

	public Optional<User> findByUsernameAndPassword(String username, String password) {
		return repository.findByUsernameAndPassword(username, password);
	}

	@Override
	public User saveUser(User user) {
		return repository.save(user);
	}

	@Override
	public List<User> findAllUsers() {
		return repository.findAll();
	}

	@Override
	public Optional<User> findUserById(int id) {
		return repository.findById(id);
	}

}
