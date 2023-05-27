package com.project.hospital.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Nurse extends Personal {

    @OneToMany(mappedBy = "nurse")
    private Set<Patient> patients;

    @ManyToOne
    @JoinColumn(name = "department")
    private Department department;

    public Nurse(String name) {
        super(name);
    }

    // TODO add patient to SetPatients
    // TODO remove patient from SetPatients
}
