package com.project.hospital.controller;

import com.project.hospital.Qualification;
import com.project.hospital.model.entities.Doctor;
import com.project.hospital.model.entities.Patient;
import com.project.hospital.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Patient updatePatient(@PathVariable Long id, @RequestBody Patient patient)
    {
        patient.setId(id);
        return patientService.updatePatient(patient);

    }
    //DELETE
    @DeleteMapping("/deleteDoctorById/{id}")
    public void deletePatientById(@PathVariable Long id){patientService.deletePatientById(id);}
}
