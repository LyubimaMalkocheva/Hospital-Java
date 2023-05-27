package com.project.hospital.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.hospital.Curing;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private String phone;
    private String healthInfo; // set by doctor
    private Curing curing; // set by doctor

    @ManyToOne
    @JoinColumn(name = "doctor")
    @JsonIgnoreProperties({"phone","healthInfo", "curing", "room", "numberNights", "payment", "money"})
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "nurse")
    @JsonIgnoreProperties({"phone","healthInfo", "curing", "room", "numberNights", "payment", "money"})
    private Nurse nurse;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
    private Integer numberNights;
    private Double obligationsToPay;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public void setCuring(Curing curing) {
        this.curing = curing;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Room getRoom() {
        return room;
    }

    public Integer getNumberNights() {
        return numberNights;
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
