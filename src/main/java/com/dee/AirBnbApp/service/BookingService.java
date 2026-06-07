package com.dee.AirBnbApp.service;

import com.dee.AirBnbApp.dto.BookingDto;
import com.dee.AirBnbApp.dto.BookingRequest;
import com.dee.AirBnbApp.dto.GuestDto;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface BookingService {
    BookingDto initialiseBooking(BookingRequest bookingRequest);

    BookingDto addGuests(Long bookingId, List<GuestDto> guestDtoList);
}
