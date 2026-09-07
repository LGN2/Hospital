package com.CV.Hospital.controllers;

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
    public Room addRoom(
            @RequestBody Room room,
            @RequestParam Long hospitalId) {

        return roomService.addRoom(room, hospitalId);
    }

    @GetMapping
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping("/{id}")
    public Room getRoomById(@PathVariable Long id) {
        return roomService.getRoomById(id);
    }

    @GetMapping("/hospital/{hospitalId}")
    public List<Room> getRoomsByHospital(
            @PathVariable Long hospitalId) {

        return roomService.getRoomsByHospital(hospitalId);
    }

    @PutMapping("/{id}")
    public Room updateRoom(
            @PathVariable Long id,
            @RequestBody Room room) {

        return roomService.updateRoom(id, room);
    }
}
