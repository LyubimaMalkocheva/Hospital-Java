package com.project.hospital.controller;

import com.project.hospital.model.entities.Patient;
import com.project.hospital.model.exceptions.NotFoundException;
import com.project.hospital.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;
    @GetMapping("/all")
    public List<Patient> getAllPatients(){
        return patientService.getAllPatients();
    }

    @GetMapping("/findPatientById/{id}")
    public Patient getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id);
    }
    //POST
    @PostMapping
    public Patient registerNewPatient(@RequestBody Patient patient)
    {
        return patientService.registerNewPatient(patient);
    }

    //PUT
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updatePatient(@PathVariable Long id, @RequestBody Patient patient)
    {
        Patient existingPatient = patientService.getPatientById(id);

        if (existingPatient != null) {
            if (patient.getName() != null)
                existingPatient.setName(patient.getName());
            if (patient.getPhone() != null)
                existingPatient.setPhone(patient.getPhone());
            if (patient.getEmail() != null)
                existingPatient.setEmail(patient.getEmail());
            if (patient.getPassword() != null)
                existingPatient.setPassword(patient.getPassword());
            if (patient.getHealthInfo() != null)
                existingPatient.setHealthInfo(patient.getHealthInfo());
            if (patient.getCuring() != null)
                existingPatient.setCuring(patient.getCuring());

            patientService.updatePatient(existingPatient);

            return ResponseEntity.ok("Patient updated successfully");
        } else {
            throw new NotFoundException("Patient not found");
        }

    }
    //DELETE
    @DeleteMapping("/deleteDoctorById/{id}")
    public void deletePatientById(@PathVariable Long id){patientService.deletePatientById(id);}
}
