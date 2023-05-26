package com.project.hospital.controller;

import com.project.hospital.model.entities.Room;
import com.project.hospital.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
