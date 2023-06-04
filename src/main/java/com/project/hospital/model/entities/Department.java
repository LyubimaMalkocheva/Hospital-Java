package com.project.hospital.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
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
    @ManyToOne
    @JoinColumn(name = "hospital")
    private Hospital hospital;

    private String name;

    @OneToOne(mappedBy = "department")
    private Doctor headOfDepartment;

    @OneToMany(mappedBy = "department")
    private Set<Doctor> doctorSet;

    @OneToMany(mappedBy = "department")
    private Set<Nurse> nurseSet;

    @OneToMany(mappedBy = "department")
    private Set<Room> roomSet;

    public Department(Hospital hospital, String name) {
        this.hospital = hospital;
        this.name = name;
        this.doctorSet = new HashSet<>();
        this.nurseSet = new HashSet<>();
        this.roomSet = new HashSet<>();
    }
}
