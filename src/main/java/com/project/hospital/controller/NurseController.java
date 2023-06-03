package com.project.hospital.controller;

import com.project.hospital.Qualification;
import com.project.hospital.model.entities.Nurse;
import com.project.hospital.service.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/nurses")
public class NurseController {
    @Autowired
    private NurseService nurseService;

    //GET
    @GetMapping("/all")
    public List<Nurse> getAllNurses(){
        return nurseService.getAllNurses();
    }

    @GetMapping("/findNurseById/{id}")
    public Nurse getNurseById(@PathVariable Long id) {
        return nurseService.getNurseById(id);
    }
    //POST
    @PostMapping
    public Nurse registerNewNurse(@RequestBody Nurse nurse)
    {
        return nurseService.registerNewNurse(nurse);
    }

    //PUT
    @PutMapping("/update/{id}")
    public ResponseEntity<Nurse> updateNurse(@PathVariable Long id, @RequestBody Nurse updatedNurse) {
        Optional<Nurse> optionalNurse = Optional.ofNullable(nurseService.getNurseById(id));
        if (optionalNurse.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Nurse existingNurse = optionalNurse.get();
        existingNurse.setName(updatedNurse.getName());
        existingNurse.setPhone(updatedNurse.getPhone());
        existingNurse.setEmail(updatedNurse.getEmail());
        existingNurse.setPassword(updatedNurse.getPassword());
        existingNurse.setDepartment(updatedNurse.getDepartment());
        Nurse savedNurse = nurseService.updateNurse(existingNurse);

        return ResponseEntity.ok(savedNurse);
    }
    //DELETE
    @DeleteMapping("/deleteDoctorById/{id}")
    public void deleteDoctorById(@PathVariable Long id){nurseService.deleteNurseById(id);}
}
