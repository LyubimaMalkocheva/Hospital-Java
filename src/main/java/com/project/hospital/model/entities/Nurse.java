package com.project.hospital.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.Set;

@Entity
public class Nurse extends Personal {


    @OneToMany(mappedBy = "nurse")
    private Set<Patient> patients;

    public Nurse(String name) {
        super(name);
    }
}
