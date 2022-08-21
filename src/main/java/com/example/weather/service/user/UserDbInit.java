package com.example.weather.service.user;

import com.example.weather.config.SecurityConfiguration;
import com.example.weather.entity.User;
import com.example.weather.enums.UserRole;
import com.example.weather.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserDbInit implements CommandLineRunner {

    private final UserRepository userRepository;
    @Autowired
    public UserDbInit(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        User admin = new User("admin","admin", "admin@email.com", SecurityConfiguration.passwordEncoder().encode("admin"), UserRole.ADMIN);

        if(this.userRepository.findAll().stream().filter(user -> user.getEmail().equals(admin.getEmail())).toList().size() > 0){
        }else{
            this.userRepository.save(admin);
        }
    }
}
