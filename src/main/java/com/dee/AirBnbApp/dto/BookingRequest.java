package com.dee.AirBnbApp.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingRequest {
    private Long hotelId;
    private Long roomId;
    private Integer roomsCount;
    private LocalDate checkInDate;
    private LocalDate checkOutdate;
}
