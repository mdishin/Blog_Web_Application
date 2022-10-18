package com.dishinmohammed.blogwebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dishinmohammed.blogwebapp.entity.User;



public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);

}
