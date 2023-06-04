package com.project.hospital.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "hospital")
    @JsonIgnoreProperties({"id","departments","staffSet"})
    private Hospital hospital;

    @OneToOne(mappedBy = "department")
    @JsonIgnoreProperties({"id","department","isHeadOfDepartment","patients","qualification"})
    private Doctor headOfDepartment;

    @OneToMany(mappedBy = "department")
    @JsonIgnoreProperties({"id","department","password","email","phone","patients","qualification"})
    private Set<Doctor> doctorSet;

    @OneToMany(mappedBy = "department")
    @JsonIgnoreProperties({"id","patients","department","password","email","phone"})
    private Set<Nurse> nurseSet;

    @OneToMany(mappedBy = "department")
    @JsonIgnoreProperties({"patients","department"})
    private Set<Room> roomSet;

    public Department(Hospital hospital, String name) {
        this.hospital = hospital;
        this.name = name;
        this.doctorSet = new HashSet<>();
        this.nurseSet = new HashSet<>();
        this.roomSet = new HashSet<>();
    }

    //TODO check the error when getting a department
}
