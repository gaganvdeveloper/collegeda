package com.softgv.cda.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softgv.cda.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByUsernameAndPassword(String username, String password);
}
