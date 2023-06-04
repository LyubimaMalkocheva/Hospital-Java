package com.project.hospital.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.hospital.Curing;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Patient extends Personal{

    private String healthInfo; // set by doctor
    private Curing curing; // set by doctor

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


    public Patient(String name, String phone, String email, String password) {
        super(name, phone, email, password);
    }

    public String getHealthInfo() {
        return healthInfo;
    }

    public void setHealthInfo(String healthInfo) {
        this.healthInfo = healthInfo;
    }

    public Curing getCuring() {
        return curing;
    }

    public Nurse getNurse() {
        return nurse;
    }

    public void setNurse(Nurse nurse) {
        this.nurse = nurse;
    }

    public void setCuring(Curing curing) {
        this.curing = curing;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Room getRoom() {
        return room;
    }

    public Double getObligationsToPay() {
        return obligationsToPay;
    }

    public void setObligationsToPay(Double obligationsToPay) {
        this.obligationsToPay = obligationsToPay;
    }

    public void calculateObligationsToPay(){
        this.obligationsToPay = this.obligationsToPay + this.curing.getDays() * this.room.getTypeRoom().getRoomPrice();
    }

    public void payObligations(){
        Hospital.calculateIncome(obligationsToPay);
        obligationsToPay = 0.;
    }

}
