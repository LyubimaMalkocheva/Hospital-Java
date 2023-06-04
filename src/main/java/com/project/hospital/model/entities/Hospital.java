package com.project.hospital.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
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

    private static Double income=0.0;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Hospital() {
    }


    public Hospital(String name, String address) {
        this.name = name;
        this.address = address;
        this.departments = new HashSet<>();
        this.staffSet = new HashSet<>();
    }

    public Hospital(String name, String address, Set<Department> departments) {
        this.name = name;
        this.address = address;
        this.departments = departments;
        this.staffSet = new HashSet<>();
    }

    public static Double  calculateIncome(Double additionalPayment){
        Hospital.income = getIncome() + additionalPayment;
        return income;
    }

    public static Double getIncome() {
        return income;
    }

}
