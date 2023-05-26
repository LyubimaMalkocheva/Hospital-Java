package com.project.hospital.controller;

import com.project.hospital.model.entities.Doctor;
import com.project.hospital.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    //POST

    //PUT

    //DELETE
}
