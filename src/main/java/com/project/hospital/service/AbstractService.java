package com.project.hospital.service;

import com.project.hospital.model.repositories.DoctorRepository;
import com.project.hospital.model.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractService {
    @Autowired
    protected RoomRepository roomRepository;
    @Autowired
    protected DoctorRepository doctorRepository;
}
