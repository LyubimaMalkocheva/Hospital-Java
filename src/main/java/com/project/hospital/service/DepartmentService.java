package com.project.hospital.service;

import com.project.hospital.model.entities.Department;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService extends AbstractService{
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }
    public Department getDepartmentById(Long id){
        return departmentRepository.findAll().stream().filter(t -> id.equals(t.getId()))
                .findFirst()
                .orElse(null);

    }
    public Department createNewDepartment(Department department) {
        return departmentRepository.save(department);
    }
    public Department updateDepartment(Department department){
        return  departmentRepository.save(department);
    }

    public void deleteDepartmentById(Long id) {
        boolean exists = departmentRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Doctor with id " + id + " does not exists");
        }
        departmentRepository.deleteById(id);
        System.out.println("Department with id - " + id + " is deleted!");
    }
}
