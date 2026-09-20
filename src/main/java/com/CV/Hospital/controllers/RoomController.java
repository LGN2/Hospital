package com.CV.Hospital.controllers;

import com.CV.Hospital.dto.RoomDTO;
import com.CV.Hospital.entities.Room;
import com.CV.Hospital.services.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping
    public RoomDTO addRoom(
            @RequestBody Room room,
            @RequestParam Long hospitalId) {

        return roomService.convertToDTO(
                roomService.addRoom(room, hospitalId)
        );
    }

    @GetMapping
    public List<RoomDTO> getAllRooms() {
        return roomService.convertToDTO(
                roomService.getAllRooms()
        );
    }

    @GetMapping("/{id}")
    public RoomDTO getRoomById(@PathVariable Long id) {
        return roomService.convertToDTO(
                roomService.getRoomById(id)
        );
    }

    @GetMapping("/hospital/{hospitalId}")
    public List<RoomDTO> getRoomsByHospital(
            @PathVariable Long hospitalId) {

        return roomService.convertToDTO(
                roomService.getRoomsByHospital(hospitalId)
        );
    }

    @PutMapping("/{id}")
    public RoomDTO updateRoom(
            @PathVariable Long id,
            @RequestBody Room room) {

        return roomService.convertToDTO(
                roomService.updateRoom(id, room)
        );
    }

    @DeleteMapping("/{id}")
    public void deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
    }
}