package com.dee.AirBnbApp.entity;

import com.dee.AirBnbApp.entity.enums.Gender;
import jakarta.annotation.security.DenyAll;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Integer age;
}
