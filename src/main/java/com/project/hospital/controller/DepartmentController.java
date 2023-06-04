package com.project.hospital.controller;


import com.project.hospital.model.entities.Department;
import com.project.hospital.model.exceptions.NotFoundException;
import com.project.hospital.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;


    //GET
    @GetMapping("/all")
    public List<Department> getAllDepartments(){
        return departmentService.getAllDepartments();
    }

    @GetMapping("/findDepartmentById/{id}")
    public Department getDepartmentById(@PathVariable Long id) {
        return departmentService.getDepartmentById(id);
    }
    //POST
    @PostMapping
    public Department createNewDepartment(@RequestBody Department department)
    {
        return departmentService.createNewDepartment(department);
    }

    //PUT
    @PutMapping("/update/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable Long id, @RequestBody Department updatedDepartment)
    {
        Optional<Department> optionalDepartment = Optional.ofNullable(departmentService.getDepartmentById(id));

        if (optionalDepartment.isPresent()) {
            Department existingDepartment = optionalDepartment.get();

            if (updatedDepartment.getName() != null)
                existingDepartment.setName(updatedDepartment.getName());
            if (updatedDepartment.getRoomSet() != null)
                existingDepartment.setRoomSet(updatedDepartment.getRoomSet());

            Department savedDepartment = departmentService.updateDepartment(existingDepartment);
            return ResponseEntity.ok(savedDepartment);
        } else {
            throw new NotFoundException("Department not found");
        }

    }
    //DELETE
    @DeleteMapping("/deleteDepartmentById/{id}")
    public void deleteDepartmentById(@PathVariable Long id){departmentService.deleteDepartmentById(id);}
}
