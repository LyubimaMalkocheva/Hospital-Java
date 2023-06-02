package com.project.hospital.service;

import com.project.hospital.model.entities.Doctor;
import com.project.hospital.model.entities.Room;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService extends AbstractService{
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room getRoomById(Long id){
        return roomRepository.findAll().stream().filter(t -> id.equals(t.getId()))
                .findFirst()
                .orElse(null);
    }

    public Room addRoom(Room room) {
        return roomRepository.save(room);
    }

    public Doctor updateDoctor(Doctor doctor){
        return  doctorRepository.save(doctor);
    }

    public Room updateRoom(Room room) {
        return roomRepository.save(room);
    }
}