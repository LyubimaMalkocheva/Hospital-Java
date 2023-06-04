package com.project.hospital.service;

import com.project.hospital.model.entities.Doctor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DoctorService extends AbstractService{
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }
    public Doctor getDoctorById(Long id){
        return doctorRepository.findAll().stream().filter(t -> id.equals(t.getId()))
                .findFirst()
                .orElse(null);

    }

    public Doctor registerNewDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }
    public Doctor updateDoctor(Doctor doctor){
        return  doctorRepository.save(doctor);
    }

    public void deleteDoctorById(Long id){
        boolean exists = doctorRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Doctor with id " + id + " does not exists");
        }
        doctorRepository.deleteById(id);
        System.out.println("Doctor with id - " + id +" is deleted!");
    }
}
