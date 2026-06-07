package com.dee.AirBnbApp.Repository;

import com.dee.AirBnbApp.dto.RoomDto;
import com.dee.AirBnbApp.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
   /* @Query("select * from rooms where hotel_id = '?'")
    List<Room> getAllRoomsInHotel(Long hotelId);*/
}
