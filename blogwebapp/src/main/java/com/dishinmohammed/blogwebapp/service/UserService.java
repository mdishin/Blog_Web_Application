package com.dishinmohammed.blogwebapp.service;

import com.dishinmohammed.blogwebapp.dto.RegistrationDto;
import com.dishinmohammed.blogwebapp.entity.User;


public interface UserService {
	void saveUser(RegistrationDto registrationDto);

    User findByEmail(String email);

}
