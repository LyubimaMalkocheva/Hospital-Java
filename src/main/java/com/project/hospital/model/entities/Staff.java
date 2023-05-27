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

    public Staff(String name) {
        super(name);
    }

    public Staff(String name, Hospital hospital) {
        super(name);
        this.hospital = hospital;
    }
}
