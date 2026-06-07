package com.dee.AirBnbApp.dto;

import lombok.Data;

import java.util.List;

@Data
public class HotelInfoDto {
    private HotelDto hotelDto;
    private List<RoomDto> roomDtoList;
}
