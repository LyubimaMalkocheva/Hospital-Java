package com.project.hospital.controller;

import com.project.hospital.model.entities.Doctor;
import com.project.hospital.model.exceptions.NotFoundException;
import com.project.hospital.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/doctors")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    //GET
    @GetMapping("/all")
    public List<Doctor> getAllDoctors(){
        return doctorService.getAllDoctors();
    }

    @GetMapping("/findDoctorById/{id}")
    public Doctor getDoctorById(@PathVariable Long id) {
        return doctorService.getDoctorById(id);
    }
    //POST
    @PostMapping
    public Doctor registerNewDoctor(@RequestBody Doctor doctor)
    {
         return doctorService.registerNewDoctor(doctor);
    }

    //PUT
    @PutMapping("/update/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable Long id, @RequestBody Doctor updatedDoctor)
    {
        Optional<Doctor> optionalDoctor = Optional.ofNullable(doctorService.getDoctorById(id));

        if (optionalDoctor.isPresent()) {
            Doctor existingDoctor = optionalDoctor.get();

            // This method doesn't update isHeadOfDepartment
            if (updatedDoctor.getName() != null) {
                existingDoctor.setName(updatedDoctor.getName());
            }

            if (updatedDoctor.getPhone() != null) {
                existingDoctor.setPhone(updatedDoctor.getPhone());
            }

            if (updatedDoctor.getEmail() != null) {
                existingDoctor.setEmail(updatedDoctor.getEmail());
            }

            if (updatedDoctor.getPassword() != null) {
                existingDoctor.setPassword(updatedDoctor.getPassword());
            }

            if (updatedDoctor.getDepartment() != null) {
                existingDoctor.setDepartment(updatedDoctor.getDepartment());
            }

            if (updatedDoctor.getQualification() != null) {
                existingDoctor.setQualification(updatedDoctor.getQualification());
            }

            Doctor savedDoctor = doctorService.updateDoctor(existingDoctor);
            return ResponseEntity.ok(savedDoctor);
        } else {
            throw new NotFoundException("Doctor not found");
        }

    }
    //DELETE
    @DeleteMapping("/deleteDoctorById/{id}")
    public void deleteDoctorById(@PathVariable Long id){doctorService.deleteDoctorById(id);}
}
