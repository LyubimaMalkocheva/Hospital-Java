package com.project.hospital.human;

import com.project.hospital.enums.Role;
import com.project.hospital.patient.Patient;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.jetbrains.annotations.NotNull;
import java.util.Set;

@Entity
public class Human {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotBlank(message = "Name should not be blank")
    private String name = null;
    private String departament;

    public Human() {
    }

    public Human(Long id, String name, String departament, boolean leader, Role role) {
        this.id = id;
        this.name = name;
        this.departament = departament;
    }

    public Human(String name, String departament, boolean leader, Role role) {
        this.name = name;
        this.departament = departament;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartament() {
        return departament;
    }

    public void setDepartament(String departament) {
        this.departament = departament;
    }
}
