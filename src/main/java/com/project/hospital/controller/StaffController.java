package com.project.hospital.controller;

import com.project.hospital.model.entities.Nurse;
import com.project.hospital.model.entities.Staff;

import com.project.hospital.model.exceptions.NotFoundException;
import com.project.hospital.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/staff")
public class StaffController {
    @Autowired
    private StaffService staffService;
    //GET
    @GetMapping("/all")
    public List<Staff> getAllStaff(){
        return staffService.getAllStaff();
    }

    @GetMapping("/findStaffById/{id}")
    public Staff getStaffById(@PathVariable Long id) {
        return staffService.getStaffById(id);
    }

    //POST
    @PostMapping
    public Staff registerNewStaff(@RequestBody Staff staff)
    {
        return staffService.registerNewStaff(staff);
    }

    //PUT
    @PutMapping("/update/{id}")
    public ResponseEntity<Staff> updateStaff(@PathVariable Long id, @RequestBody Staff updatedStaff) {
        Optional<Staff> optionalStaff = Optional.ofNullable(staffService.getStaffById(id));
        if (optionalStaff.isEmpty()) {
            throw new NotFoundException("Staff not found");
        }
        Staff existingStaff = optionalStaff.get();
        if (updatedStaff.getName() != null)
            existingStaff.setName(updatedStaff.getName());
        if (updatedStaff.getPhone() != null)
            existingStaff.setPhone(updatedStaff.getPhone());
        if (updatedStaff.getEmail() != null)
            existingStaff.setEmail(updatedStaff.getEmail());
        if (updatedStaff.getPassword() != null)
            existingStaff.setPassword(updatedStaff.getPassword());
        if (updatedStaff.getHospital() != null)
            existingStaff.setHospital(updatedStaff.getHospital());
        Staff savedStaff = staffService.updateStaff(existingStaff);

        return ResponseEntity.ok(savedStaff);
    }
    //DELETE
    @DeleteMapping("/deleteStaffById/{id}")
    public void deleteStaffById(@PathVariable Long id){staffService.deleteStaffById(id);}

}
