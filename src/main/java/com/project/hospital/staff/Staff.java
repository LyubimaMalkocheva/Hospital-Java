package com.project.hospital.staff;

import com.project.hospital.enums.Role;
import com.project.hospital.human.Human;
import jakarta.persistence.Entity;

@Entity
public class Staff extends Human {
    private Role role;

    public Staff() {
    }

    public Staff(String name, String departament, boolean leader, Role role, Role role1) {
        super(name, departament, leader, role);
        this.role = role1;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
