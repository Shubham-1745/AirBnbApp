package com.dee.AirBnbApp.entity;

import com.dee.AirBnbApp.entity.enums.Role;
import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Setter
@Getter
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;  //encoded

    private String name;

    @ElementCollection(fetch = FetchType.EAGER) // it will create table with name app_user_roles;
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;
}
