package com.CV.Hospital.controllers;

import com.CV.Hospital.entities.Room;
import com.CV.Hospital.services.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
}
