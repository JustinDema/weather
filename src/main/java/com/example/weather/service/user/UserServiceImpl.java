package com.example.weather.service.user;

import com.example.weather.config.SecurityConfiguration;
import com.example.weather.entity.User;
import com.example.weather.enums.UserRole;
import com.example.weather.exceptions.ResourceNotFoundException;
import com.example.weather.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository)  {
        this.userRepository = userRepository;
    }
    @Override
    public User findUserByEmail(String email) throws ResourceNotFoundException {

        return Optional.ofNullable(userRepository.findByEmail(email))
                .orElseThrow(()->new ResourceNotFoundException("User.email not found"));
    }


    @Override
    public User save(UserRegistrationDto registrationDto) {
        User user = new User(
                registrationDto.getUserName(),
                registrationDto.getUserLastName(),
                registrationDto.getEmail(),
                SecurityConfiguration.passwordEncoder().encode(registrationDto.getPassword()),
                UserRole.USER);
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user==null){
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(Collections.singleton(user.getUserRole())));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<UserRole> roles){
        return roles.stream().map(role ->
                        new SimpleGrantedAuthority(role.toString()))
                .collect(Collectors.toList());
    }

    public String loggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            return currentUserName;
        }
        return "not found";
    }
}

