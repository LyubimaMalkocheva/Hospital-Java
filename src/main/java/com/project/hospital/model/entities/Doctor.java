package com.project.hospital.model.entities;

import com.project.hospital.Curing;
import com.project.hospital.Qualification;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@NoArgsConstructor
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
    private Qualification qualification;

    public Doctor(String name, Department department, boolean isHeadOfDepartment, Qualification qualification) {
        this.name = name;
        this.department = department;
        this.isHeadOfDepartment = isHeadOfDepartment;
        this.qualification = qualification;
    }
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

    public Qualification getQualification() {
        return qualification;
    }

    public void setQualification(Qualification qualification) {
        this.qualification = qualification;
    }

    public void setPatientsCuring(int id, Curing curing)
    {
        int i=0;
        for(Patient patient: patients)
        {
            if(id==i) patient.setCuring(curing);
            i++;
        }
    }

    public void setPatientsHealthInfo(int id, String healthInfo)
    {
        int i=0;
        for(Patient patient: patients)
        {
            if(id==i) patient.setHealthInfo(healthInfo);
            i++;
        }
    }


}

