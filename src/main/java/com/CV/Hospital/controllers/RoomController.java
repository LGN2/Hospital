package com.CV.Hospital.controllers;

import com.CV.Hospital.dto.RoomDTO;
import com.CV.Hospital.services.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseStatus(HttpStatus.CREATED)
    public RoomDTO addRoom(
            @Valid @RequestBody RoomDTO dto) {
        return roomService.addRoom(dto);
    }

    @GetMapping
    public List<RoomDTO> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping("/{id}")
    public RoomDTO getRoomById(@PathVariable Long id) {
        return roomService.getRoomById(id);
    }

    @GetMapping("/hospital/{hospitalId}")
    public List<RoomDTO> getRoomsByHospital(
            @PathVariable Long hospitalId) {
        return roomService.getRoomsByHospital(hospitalId);
    }

    @PutMapping("/{id}")
    public RoomDTO updateRoom(
            @PathVariable Long id,
            @Valid @RequestBody RoomDTO dto) {
        return roomService.updateRoom(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
    }

    @GetMapping("/available")
    public List<RoomDTO> getRoomsWithAvailableCapacity() {

        return roomService
                .getRoomsWithAvailableCapacity();
    }

    @GetMapping("/hospital/{hospitalId}/available")
    public List<RoomDTO> getAvailableRoomsByHospital(
            @PathVariable Long hospitalId) {

        return roomService
                .getAvailableRoomsByHospital(hospitalId);
    }
}