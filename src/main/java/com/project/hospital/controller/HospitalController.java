package com.project.hospital.controller;

import com.project.hospital.model.entities.Hospital;
import com.project.hospital.model.exceptions.NotFoundException;
import com.project.hospital.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/hospital")
public class HospitalController {
    @Autowired
    private HospitalService hospitalService;
    //PUT
    @PutMapping("/update")
    public ResponseEntity<Hospital> updateHospital(@RequestBody Hospital updatedHospital)
    {
        Optional<Hospital> optionalHospital = Optional.ofNullable(hospitalService.getHospital());

        if (optionalHospital.isPresent()) {
           Hospital existingHospital = optionalHospital.get();
           if (updatedHospital.getName() != null)
               existingHospital.setName(updatedHospital.getName());
           if (updatedHospital.getAddress() != null)
               existingHospital.setAddress(updatedHospital.getAddress());
            Hospital savedHospital = hospitalService.updateHospital(existingHospital);
            return ResponseEntity.ok(savedHospital);
        } else {
            throw new NotFoundException("Hospital not found");
        }

    }
}
