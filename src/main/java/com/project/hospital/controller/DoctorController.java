package com.project.hospital.controller;

import com.project.hospital.Qualification;
import com.project.hospital.model.entities.Doctor;
import com.project.hospital.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Doctor updateDoctor(@PathVariable Qualification qualification, @RequestBody Doctor doctor)
    {
        doctor.setQualification(qualification);
        return doctorService.updateDoctor(doctor);

    }
    //DELETE
    @DeleteMapping("/deleteDoctorById/{id}")
    public void deleteDoctorById(@PathVariable Long id){doctorService.deleteDoctorById(id);}
}
