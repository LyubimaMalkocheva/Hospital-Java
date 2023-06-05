// Created login and registration - 05.06.2023 from Georgi Shishinyov
// Created email validation - 05.06.2023 from Georgi Shishinyov

package com.project.hospital.controller;

import com.project.hospital.model.DTOs.PatientDTOs.*;
import com.project.hospital.model.entities.Patient;
import com.project.hospital.model.exceptions.NotFoundException;
import com.project.hospital.service.PatientService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/patients")
public class PatientController extends AbstractController {
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

    @PostMapping("/patients")
    public PatientWithoutPassDTO register(@Valid @RequestBody RegisterDTO dto){
        return patientService.register(dto);
    }

    @PostMapping("/patients/login")
    public PatientWithoutPassDTO login(@Valid @RequestBody LoginDTO dto) {
        return patientService.login(dto);
    }

    @PostMapping("/patients/logout")
    public void logout(HttpSession s) {
        // Check if the patient is logged in
        getLoggedPatientId(s);
        s.invalidate();
    }

    @PutMapping("/users/{id}")
    public PatientWithoutPassDTO editPatientById(@PathVariable int id, @Valid @RequestBody PatientEditDTO editDto, HttpSession s) {
        return patientService.updatePatientById(id, editDto, getLoggedPatientId(s));
    }

    @DeleteMapping("/users/{id}")
    public PatientWithoutPassDTO deletePatientById(@PathVariable int id, HttpSession s) {
        return patientService.deletePatientById(id, getLoggedPatientId(s));
    }

    @GetMapping("/email-validation")
    public PatientWithoutPassDTO validateEmail(@RequestParam("code") String code) {
        return patientService.validateCode(code);
    }

    @PutMapping("/users/{id}/password-change")
    public PatientWithoutPassDTO changePassword(@Valid @RequestBody PatientPasswordChangeDTO passwordChangeDTO, @PathVariable int id, HttpSession s) {
        return patientService.changePassword(id, passwordChangeDTO, getLoggedPatientId(s));
    }

//    @PostMapping
//    public Patient registerNewPatient(@RequestBody Patient patient)
//    {
//        return patientService.registerNewPatient(patient);
//    }
//
//    //PUT
//    @PutMapping("/update/{id}")
//    public ResponseEntity<String> updatePatient(@PathVariable Long id, @RequestBody Patient patient)
//    {
//        Patient existingPatient = patientService.getPatientById(id);
//
//        if (existingPatient != null) {
//            if (patient.getName() != null)
//                existingPatient.setName(patient.getName());
//            if (patient.getPhone() != null)
//                existingPatient.setPhone(patient.getPhone());
//            if (patient.getEmail() != null)
//                existingPatient.setEmail(patient.getEmail());
//            if (patient.getPassword() != null)
//                existingPatient.setPassword(patient.getPassword());
//            if (patient.getHealthInfo() != null)
//                existingPatient.setHealthInfo(patient.getHealthInfo());
//            if (patient.getCuring() != null)
//                existingPatient.setCuring(patient.getCuring());
//
//            patientService.updatePatient(existingPatient);
//
//            return ResponseEntity.ok("Patient updated successfully");
//        } else {
//            throw new NotFoundException("Patient not found");
//        }
//
//    }
//    //DELETE
//    @DeleteMapping("/deleteDoctorById/{id}")
//    public void deletePatientById(@PathVariable Long id, HttpSession s){patientService.deletePatientById(id, getLoggedPatientId(s));}
}
