package com.project.hospital.service;

import com.project.hospital.model.repositories.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractService {
    @Autowired
    protected RoomRepository roomRepository;
    @Autowired
    protected DoctorRepository doctorRepository;
    @Autowired
    protected NurseRepository nurseRepository;
    @Autowired
    protected StaffRepository staffRepository;
    @Autowired
    protected DepartmentRepository departmentRepository;
    @Autowired
    protected HospitalRepository hospitalRepository;

    @Autowired
    protected ModelMapper mapper;
}
