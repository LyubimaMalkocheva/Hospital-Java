package com.project.hospital.service;


import com.project.hospital.model.entities.Staff;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffService extends AbstractService{
    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    public Staff getStaffById(Long id){
        return staffRepository.findAll().stream().filter(t -> id.equals(t.getId()))
                .findFirst()
                .orElse(null);
    }

    public Staff registerNewStaff(Staff staff) {
        return staffRepository.save(staff);
    }

    public Staff updateStaff(Staff staff){
        return  staffRepository.save(staff);
    }

    public void deleteStaffById(Long id){
        boolean exists = staffRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Staff with id " + id + " does not exists");
        }else {
            staffRepository.deleteById(id);
            System.out.println("Staff with id - " + id + " is deleted!");
        }
    }
}
