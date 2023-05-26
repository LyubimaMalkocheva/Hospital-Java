package com.project.hospital.service;

import com.project.hospital.model.entities.Doctor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DoctorService extends AbstractService{
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }
}
