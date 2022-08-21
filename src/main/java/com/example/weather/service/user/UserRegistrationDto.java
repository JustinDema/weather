package com.example.weather.service.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRegistrationDto {
    private String userName;
    private String userLastName;
    private String email;
    private String password;
}
