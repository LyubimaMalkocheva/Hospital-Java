package com.project.hospital.service;

import com.project.hospital.model.entities.Room;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService extends AbstractService{
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }
}