package com.dee.AirBnbApp.controller;

import com.dee.AirBnbApp.dto.HotelDto;
import com.dee.AirBnbApp.dto.HotelInfoDto;
import com.dee.AirBnbApp.dto.HotelSearchRequest;
import com.dee.AirBnbApp.service.HotelService;
import com.dee.AirBnbApp.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/hotels")
public class HotelBrowseController {
    private final InventoryService inventoryService;
    private final HotelService hotelService;
    @GetMapping("/search")
    public ResponseEntity<Page<HotelDto>> searchHotels(@RequestBody HotelSearchRequest hotelSearchRequest){
        Page<HotelDto> page = inventoryService.searchHotels(hotelSearchRequest);

        return ResponseEntity.ok(page);
    }

    @GetMapping("{hotelId}/info")
    public ResponseEntity<HotelInfoDto> getHotelInfo(@PathVariable Long hotelId){
        return ResponseEntity.ok(hotelService.getHotelInfoById(hotelId));
    }
}
