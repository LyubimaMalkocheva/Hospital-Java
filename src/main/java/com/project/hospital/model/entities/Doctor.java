package com.project.hospital.model.entities;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "department")
    private Department department;

    private boolean isHeadOfDepartment = false;

    @OneToMany(mappedBy = "doctor")
    private Set<Patient> patients;

    public Doctor(String name, Department department, boolean isHeadOfDepartment) {
        this.name = name;
        this.department = department;
        this.isHeadOfDepartment = isHeadOfDepartment;
    }

    public Doctor(String name, Department department) {
        this.name = name;
        this.department = department;
    }

    public boolean isHeadOfDepartment() {
        return isHeadOfDepartment;
    }

    public void setHeadOfDepartment(boolean headOfDepartment) {
        this.isHeadOfDepartment = headOfDepartment;
    }

    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }

    public int getId() { return id; }

    public String getName() { return name;}

    public Department getDepartment() { return department;}

    public void setName(String name) {this.name = name;}

    public void setDepartment(Department department) {this.department = department;}
}

