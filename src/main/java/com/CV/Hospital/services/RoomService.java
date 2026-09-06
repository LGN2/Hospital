package com.CV.Hospital.services;

import com.CV.Hospital.entities.Hospital;
import com.CV.Hospital.entities.Room;
import com.CV.Hospital.repositories.HospitalRepository;
import com.CV.Hospital.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final HospitalRepository hospitalRepository;

    public Room addRoom(Room room, Long hospitalId) {
        Hospital hospital =
                hospitalRepository.findByIdAndIsActiveTrue(hospitalId)
                        .orElseThrow(() ->
                                new RuntimeException("Hospital not found"));
        if (room.getCapacity() == null || room.getCapacity() <= 0) {
            throw new IllegalArgumentException(
                    "Room capacity must be greater than zero"
            );
        }
        room.setHospital(hospital);
        room.setIsActive(true);
        return roomRepository.save(room);
    }

    public List<Room> getAllRooms() {
        return roomRepository.findByIsActiveTrue();
    }

}
