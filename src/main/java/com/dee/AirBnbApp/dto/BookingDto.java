package com.dee.AirBnbApp.dto;

import com.dee.AirBnbApp.entity.Hotel;
import com.dee.AirBnbApp.entity.Room;
import com.dee.AirBnbApp.entity.User;
import com.dee.AirBnbApp.entity.enums.BookingStatus;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class BookingDto {
    private Long id;
    private  Integer roomsCount;
    private LocalDate checkInDate;
    private LocalDate checkOutdate;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
    private BookingStatus bookingStatus;
    private Set<GuestDto> guests;

}
