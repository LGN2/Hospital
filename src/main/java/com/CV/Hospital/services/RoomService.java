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

    public Room getRoomById(Long id) {
        return roomRepository.findByIdAndIsActiveTrue(id)
                .orElseThrow(() ->
                        new RuntimeException("Room not found"));
    }

    public List<Room> getRoomsByHospital(Long hospitalId) {
        return roomRepository.findByHospitalIdAndIsActiveTrue(hospitalId);
    }

    public Room updateRoom(Long id, Room updatedRoom) {
        Room room = getRoomById(id);
        if (updatedRoom.getRoomNumber() != null) {
            room.setRoomNumber(updatedRoom.getRoomNumber());
        }
        if (updatedRoom.getFloor() != null) {
            room.setFloor(updatedRoom.getFloor());
        }
        if (updatedRoom.getType() != null) {
            room.setType(updatedRoom.getType());
        }
        if (updatedRoom.getCapacity() != null) {
            if (updatedRoom.getCapacity() <= 0) {
                throw new IllegalArgumentException(
                        "Room capacity must be greater than zero"
                );
            }
            room.setCapacity(updatedRoom.getCapacity());
        }
        return roomRepository.save(room);
    }


}
