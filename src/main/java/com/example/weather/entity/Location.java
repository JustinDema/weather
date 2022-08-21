package com.example.weather.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NonNull
    @Column(name = "location_id")
    private Long locationId;

    @NonNull
    @ManyToOne
    private User user;

    @NonNull
    @Column(name = "longitude")
    private Double longitude;

    @NonNull
    @Column(name = "latitude")
    private Double latitude;

    @NonNull
    @Column(name = "city")
    private String cityName;

    @NonNull
    @Column(name = "country")
    private String countryName;

    public Location(@NonNull User user, @NonNull Double longitude, @NonNull Double latitude, @NonNull String cityName, @NonNull String countryName) {
        this.user = user;
        this.longitude = longitude;
        this.latitude = latitude;
        this.cityName = cityName;
        this.countryName = countryName;
    }
}
