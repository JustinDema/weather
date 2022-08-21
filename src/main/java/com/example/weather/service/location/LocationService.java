package com.example.weather.service.location;

import com.example.weather.entity.Location;
import com.example.weather.service.user.UserRegistrationDto;

public interface LocationService {
    Location save(LocationRegistrationDto locationRegistrationDto);
}
