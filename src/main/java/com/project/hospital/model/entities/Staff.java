package com.project.hospital.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Staff extends Personal {
    @ManyToOne
    @JoinColumn(name = "hospital")
    private Hospital hospital;

    public Staff(String name, String phone, String email, String password) {
        super(name, phone, email, password);
    }

    public Staff(String name, String phone, String email, String password, Hospital hospital) {
        super(name, phone, email, password);
        this.hospital = hospital;
    }
}
