package com.example.weather.service.user;

import com.example.weather.entity.User;
import com.example.weather.exceptions.ResourceNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto userRegistrationDto) throws ResourceNotFoundException;

    User findUserByEmail(String email) throws ResourceNotFoundException;
}
