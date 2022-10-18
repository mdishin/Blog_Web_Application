package com.dishinmohammed.blogwebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dishinmohammed.blogwebapp.entity.Role;



public interface RoleRepository extends JpaRepository<Role, Long> {
	 Role findByName(String name);

}
