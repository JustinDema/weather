package com.example.weather.entity;

import com.example.weather.enums.UserRole;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NonNull
    @Column(name = "user_id")
    private Long userId;
    @NonNull
    @Column(name = "user_name")
    private String userName;
    @NonNull
    @Column(name = "user_last_name")
    private String userLastName;
    @NonNull
    @Column(name = "email")
    private String email;
    @NonNull
    @Column(name = "password")
    private String password;
    @NonNull
    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @OneToMany(mappedBy = "user")
    private List<Location> locations;

    public User(@NonNull String userName, @NonNull String userLastName, @NonNull String email, @NonNull String password, @NonNull UserRole userRole) {
        this.userName = userName;
        this.userLastName = userLastName;
        this.email = email;
        this.password = password;
        this.userRole = userRole;
    }
}
