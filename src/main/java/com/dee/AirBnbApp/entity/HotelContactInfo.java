package com.dee.AirBnbApp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Embeddable
public class HotelContactInfo {

    private String address;
    private String phoneNumber;
    private String location;
    private String email;
}
