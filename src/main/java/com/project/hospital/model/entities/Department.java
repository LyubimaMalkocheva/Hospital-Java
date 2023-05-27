package com.project.hospital.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @OneToOne
    @JoinColumn(name = "head_of_department_id")
    private Doctor headOfDepartment;

    @OneToMany(mappedBy = "department")
    private Set<Doctor> doctorList;

    @OneToMany(mappedBy = "department")
    private Set<Nurse> nurses;

    @OneToMany(mappedBy = "department")
    private Set<Room> roomSet;


}
