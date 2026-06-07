package com.dee.AirBnbApp.service;

import com.dee.AirBnbApp.dto.HotelDto;
import com.dee.AirBnbApp.dto.HotelSearchRequest;
import com.dee.AirBnbApp.entity.Room;
import org.springframework.data.domain.Page;

public interface InventoryService {
    void initializeRoomForAYear(Room room);
    void deleteAllInventories(Room room);

    Page<HotelDto> searchHotels(HotelSearchRequest hotelSearchRequest);
}
