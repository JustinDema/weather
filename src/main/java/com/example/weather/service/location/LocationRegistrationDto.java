package com.example.weather.service.location;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LocationRegistrationDto {
    private Double longitude;
    private Double latitude;
    private String cityName;
    private String countryName;
}
