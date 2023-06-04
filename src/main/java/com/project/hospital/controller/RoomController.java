package com.project.hospital.controller;

import com.project.hospital.Qualification;
import com.project.hospital.model.entities.Doctor;
import com.project.hospital.model.entities.Room;
import com.project.hospital.model.exceptions.NotFoundException;
import com.project.hospital.service.DoctorService;
import com.project.hospital.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;

    //GET
    @GetMapping("/all")
    public List<Room> getAllRooms(){
        return roomService.getAllRooms();
    }

    @GetMapping("/findRoomByNumber/{id}")
    public Room getRoomById(@PathVariable Long id) {
        return roomService.getRoomById(id);
    }

    @PostMapping
    public Room addRoom(@RequestBody Room room)
    {
        return roomService.addRoom(room);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable Long id, @RequestBody Room updatedRoom) {
        Optional<Room> optionalRoom = Optional.ofNullable(roomService.getRoomById(id));

        if (optionalRoom.isPresent()) {
            Room existingRoom = optionalRoom.get();

            if (updatedRoom.getAvailableBeds() != null)
                existingRoom.setAvailableBeds(updatedRoom.getAvailableBeds());

            if (updatedRoom.getTypeRoom() != null)
                existingRoom.setTypeRoom(updatedRoom.getTypeRoom());

            Room savedRoom = roomService.updateRoom(existingRoom);
            return ResponseEntity.ok(savedRoom);
        } else {
            throw new NotFoundException("Room not found");
        }
    }
}