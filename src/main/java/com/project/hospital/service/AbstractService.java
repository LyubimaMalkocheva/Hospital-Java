package com.project.hospital.service;

import com.project.hospital.HospitalApplication;
import com.project.hospital.model.exceptions.repositories.*;
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
}
