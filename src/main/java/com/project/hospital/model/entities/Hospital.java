package com.project.hospital.model.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;

    @OneToMany(mappedBy = "hospital")
    private Set<Department> departments;

    @OneToMany(mappedBy = "hospital")
    private Set<Staff> staffSet;

    private static Double income;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Hospital() {
    }

    public Hospital(String name, String address, Set<Department> departments, Set<Room> roomList) {
        this.name = name;
        this.address = address;
        this.departments = departments;
    }

    public static Double  calculateIncome(Double additionalPayment){
        Hospital.income = getIncome() + additionalPayment;
        return income;
    }

    public static Double getIncome() {
        return income;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }

}
