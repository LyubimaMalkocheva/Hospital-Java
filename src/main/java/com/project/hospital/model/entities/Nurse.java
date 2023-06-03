package com.project.hospital.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Nurse extends Personal {

    @JsonIgnoreProperties({"nurse"})
    @OneToMany(mappedBy = "nurse")
    private Set<Patient> patients;

    @ManyToOne
    @JoinColumn(name = "department")
    private Department department;

    public Nurse(String name, String phone, String email, String password, Department department) {
        super(name, phone, email, password);
        this.department = department;
        this.patients = new HashSet<>();

    }

    public void addPatient(Patient patient) {
        if (patients == null) {
            patients = new HashSet<>();
        }
        patients.add(patient);
        patient.setNurse(this);
    }

    public void removePatient(Patient patient) {
        if (patients != null) {
            patients.remove(patient);
            patient.setNurse(null);
        }
    }
}
