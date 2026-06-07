package com.dee.AirBnbApp.service;

import com.dee.AirBnbApp.Repository.HotelRepository;
import com.dee.AirBnbApp.Repository.InventoryRepository;
import com.dee.AirBnbApp.Repository.RoomRepository;
import com.dee.AirBnbApp.dto.HotelDto;
import com.dee.AirBnbApp.dto.HotelInfoDto;
import com.dee.AirBnbApp.dto.RoomDto;
import com.dee.AirBnbApp.entity.Hotel;
import com.dee.AirBnbApp.entity.Room;
import com.dee.AirBnbApp.execption.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService{
    private final HotelRepository hotelRepository;
    private final InventoryService inventoryService;
    private final ModelMapper modelMapper;
    private final RoomRepository roomRepository;

    @Override
    public HotelDto createNewHotel(HotelDto hotelDto) {
        log.info("Creating a new hotel with name: {}",hotelDto.getName());
        Hotel hotel = modelMapper.map(hotelDto, Hotel.class);
        hotel.setActive(false);
        hotel = hotelRepository.save(hotel);
        log.info("Created a hotel with Id: {}", hotel.getId());
        return modelMapper.map(hotel, HotelDto.class);
    }

    @Override
    public HotelDto getHotelById(Long id) {
        log.info("Getting the hotel details with Id: {}", id);
        Hotel hotel = hotelRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Hotel not found with this Id: "+id));

        return modelMapper.map(hotel, HotelDto.class);
    }

    @Override
    public HotelDto updateHotelById(Long id, HotelDto hotelDto) {
        log.info("Updating the hotel with Id: {}", id);
        Hotel hotel = hotelRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Hotel not found with this Id: "+id));

        modelMapper.map(hotelDto, hotel);
        hotel.setId(id);
        hotel = hotelRepository.save(hotel);
        return modelMapper.map(hotel, HotelDto.class);
    }

    @Override
    @Transactional
    public void deleteHotelById(Long hotelId) {
        log.info("delete the hotel with Id: {}",hotelId);
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(()-> new ResourceNotFoundException("Hotel not found with this Id: "+hotelId));
        //TODO-D: delete the future inventories for this hotel
        for(Room room: hotel.getRooms()){
            inventoryService.deleteAllInventories(room);
            roomRepository.deleteById(room.getId());
        }
        hotelRepository.deleteById(hotelId);
    }

    @Override
    @Transactional
    public void activateHotel(Long hotelId) {
        log.info("Activating the hotel with Id: {}", hotelId);
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(()-> new ResourceNotFoundException("Hotel not found with this Id: "+hotelId));
        hotel.setActive(Boolean.TRUE);
        //TODO-D: Create inventory for all the rooms for this hotel.
        // assuming only do it once.
        for(Room room: hotel.getRooms()){
            inventoryService.initializeRoomForAYear(room);
        }
        hotelRepository.save(hotel);

    }

    @Override
    public HotelInfoDto getHotelInfoById(Long hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(()-> new ResourceNotFoundException("Hotel not found with this Id: "+hotelId));
        HotelInfoDto hotelInfoDto = new HotelInfoDto();
        hotelInfoDto.setHotelDto(modelMapper.map(hotel, HotelDto.class));
        hotelInfoDto.setRoomDtoList(hotel.getRooms().stream()
                .map((element) -> modelMapper.map(element, RoomDto.class))
                .collect(Collectors.toList()));

        return hotelInfoDto;
    }
}
