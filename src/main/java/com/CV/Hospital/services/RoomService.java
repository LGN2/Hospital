package com.CV.Hospital.services;

import com.CV.Hospital.dto.RoomDTO;
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

    public RoomDTO addRoom(RoomDTO dto) {

        Hospital hospital = hospitalRepository
                .findByIdAndIsActiveTrue(dto.getHospitalId())
                .orElseThrow(() ->
                        new RuntimeException("Hospital not found"));

        Room room = new Room();

        room.setRoomNumber(dto.getRoomNumber());
        room.setFloor(dto.getFloor());
        room.setType(dto.getType());
        room.setCapacity(dto.getCapacity());
        room.setHospital(hospital);

        return convertToDTO(roomRepository.save(room));
    }

    public List<RoomDTO> getAllRooms() {
        return convertToDTO(roomRepository.findByIsActiveTrue());
    }

    public RoomDTO getRoomById(Long id) {
        return convertToDTO(findActiveRoom(id));
    }

    public List<RoomDTO> getRoomsByHospital(Long hospitalId) {
        return convertToDTO(
                roomRepository
                        .findByHospitalIdAndIsActiveTrue(hospitalId)
        );
    }

    public RoomDTO updateRoom(Long id, RoomDTO dto) {

        Room room = findActiveRoom(id);

        Hospital hospital = hospitalRepository
                .findByIdAndIsActiveTrue(dto.getHospitalId())
                .orElseThrow(() ->
                        new RuntimeException("Hospital not found"));

        room.setRoomNumber(dto.getRoomNumber());
        room.setFloor(dto.getFloor());
        room.setType(dto.getType());
        room.setCapacity(dto.getCapacity());
        room.setHospital(hospital);

        return convertToDTO(roomRepository.save(room));
    }

    public void deleteRoom(Long id) {
        Room room = findActiveRoom(id);
        room.setIsActive(false);
        roomRepository.save(room);
    }

    private Room findActiveRoom(Long id) {
        return roomRepository.findByIdAndIsActiveTrue(id)
                .orElseThrow(() ->
                        new RuntimeException("Room not found"));
    }

    public RoomDTO convertToDTO(Room room) {
        return RoomDTO.builder()
                .id(room.getId())
                .roomNumber(room.getRoomNumber())
                .floor(room.getFloor())
                .type(room.getType())
                .capacity(room.getCapacity())
                .hospitalId(room.getHospital().getId())
                .build();
    }

    public List<RoomDTO> convertToDTO(List<Room> rooms) {
        return rooms.stream()
                .map(this::convertToDTO)
                .toList();
    }
}