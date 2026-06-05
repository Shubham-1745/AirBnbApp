package com.dee.AirBnbApp.service;

import com.dee.AirBnbApp.Repository.HotelRepository;
import com.dee.AirBnbApp.dto.HotelDto;
import com.dee.AirBnbApp.entity.Hotel;
import com.dee.AirBnbApp.execption.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService{

    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;

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

        hotel = hotelRepository.save(hotel);
        return modelMapper.map(hotel, HotelDto.class);
    }

    @Override
    public void deleteHotelById(Long id) {
        log.info("delete the hotel with Id: {}", id);
        boolean exists = hotelRepository.existsById(id);
        if(!exists){
            throw new ResourceNotFoundException("Hotel not found with this Id: "+id);
        }
        hotelRepository.deleteById(id);
        //TODO: delete the future inventories for this hotel
    }
}
