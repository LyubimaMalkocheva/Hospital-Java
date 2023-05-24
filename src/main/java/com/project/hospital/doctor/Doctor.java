package com.project.hospital.doctor;

import com.project.hospital.enums.Role;
import com.project.hospital.human.Human;
import com.project.hospital.patient.Patient;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Doctor extends Human {
    private boolean leader;
    private Role role;// this role is for 1. Възможност за задаване на роли на потребителите на системата
                        //  (администратор, лекар, пациент, медицинска сестра, поддръжка).
    @OneToMany(mappedBy = "doctor")
    private Set<Patient> patients;

    public Doctor() {
        super();
    }

    public Doctor(String name, String departament, boolean leader, Role role, boolean leader1, Role role1) {
        super(name, departament, leader, role);
        this.leader = leader1;
        this.role = role1;
    }

    public boolean isLeader() {
        return leader;
    }

    public void setLeader(boolean leader) {
        this.leader = leader;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }
}
