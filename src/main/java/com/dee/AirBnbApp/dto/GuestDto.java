package com.dee.AirBnbApp.dto;

import com.dee.AirBnbApp.entity.User;
import com.dee.AirBnbApp.entity.enums.Gender;
import lombok.Data;

@Data
public class GuestDto {

    private Long id;
    private User user;
    private String name;
    private Gender gender;
    private Integer age;
}
