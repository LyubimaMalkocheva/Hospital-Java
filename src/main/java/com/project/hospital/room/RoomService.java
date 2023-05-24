package com.project.hospital.room;

import com.project.hospital.doctor.Doctor;
import com.project.hospital.doctor.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

}