package com.dee.AirBnbApp.controller;

import com.dee.AirBnbApp.dto.HotelDto;
import com.dee.AirBnbApp.entity.Hotel;
import com.dee.AirBnbApp.service.HotelService;
import com.dee.AirBnbApp.service.HotelServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/hotels")
@Slf4j
@RequiredArgsConstructor
public class HotelController {
    private final HotelService hotelService;

    @PostMapping()
    public ResponseEntity<HotelDto> createNewhotel(@RequestBody HotelDto hotelDto){
        log.info("Attemping to create a new hotel with name {}", hotelDto.getName());
        HotelDto hotel = hotelService.createNewHotel(hotelDto);
        return new ResponseEntity<>(hotel, HttpStatus.CREATED);
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<HotelDto> getHotelById(@PathVariable Long hotelId){
        log.info("Attemping to get the Hotel detail for Id: {}", hotelId);
        HotelDto hotelDto = hotelService.getHotelById(hotelId);

        return ResponseEntity.ok(hotelDto);
    }

    @PutMapping("/{hotelId")
    public ResponseEntity<HotelDto> updateHotelById(@PathVariable Long hotelId, @RequestBody HotelDto hotelDto){
        log.info("Attemping to update the Hotel detail for Id: {}", hotelId);

        HotelDto hotelDto1 = hotelService.updateHotelById(hotelId, hotelDto);
        return ResponseEntity.ok(hotelDto1);
    }

    @PutMapping("/{hotelId")
    public ResponseEntity<Void> updateHotelById(@PathVariable Long hotelId){
        log.info("Attemping to delete the Hotel detail for Id: {}", hotelId);

        hotelService.deleteHotelById(hotelId);
        return ResponseEntity.noContent().build();
    }
}
