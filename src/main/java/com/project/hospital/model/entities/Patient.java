package com.project.hospital.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.hospital.Curing;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Entity
@NoArgsConstructor
@Setter
@Getter
public class Patient extends Personal{

    @Column(name = "health_info")
    private String healthInfo; // set by doctor

    @Column(name = "curing")
    private Curing curing; // set by doctor

    @Column(name = "unique_code")
    private String uniqueCode;

    @Column(name = "is_verified")
    private boolean isVerified;

    @ManyToOne
    @JoinColumn(name = "doctor")
    @JsonIgnoreProperties({"id","department","password","email","phone","patients","qualification"})
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "nurse")
    @JsonIgnoreProperties({"id","patients","department","password","email","phone"})
    private Nurse nurse;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
    private Double obligationsToPay=0.0;
    private LocalDateTime expirationDate;

    public Patient(String name, String phone, String email, String password) {
        super(name, phone, email, password);
    }

    public void calculateObligationsToPay(){
        this.obligationsToPay = this.obligationsToPay + this.curing.getDays() * this.room.getTypeRoom().getRoomPrice();
    }

    public void payObligations(){
        Hospital.calculateIncome(obligationsToPay);
        obligationsToPay = 0.;
    }

}
