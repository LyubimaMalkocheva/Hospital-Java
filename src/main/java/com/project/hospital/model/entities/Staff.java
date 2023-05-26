package com.project.hospital.model.entities;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Staff extends Personal {

    public Staff(String name) {
        super(name);
    }
}
