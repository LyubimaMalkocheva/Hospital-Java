package com.project.hospital.patient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.hospital.doctor.Doctor;
import com.project.hospital.room.Room;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private String phone;
    private String healthInfo;
    private String curing;

    @ManyToOne
    @JoinColumn(name = "doctor")
    @JsonIgnoreProperties({"phone","healthInfo", "curing", "room", "numberNights", "payment", "money"})
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
    private Integer numberNights;
    private boolean payment;
    private Double money;

    public Patient() {
    }

    public Patient(String name, String phone, String healthInfo, String curing, Doctor doctor, boolean payment) {
        this.name = name;
        this.phone = phone;
        this.healthInfo = healthInfo;
        this.curing = curing;
        this.doctor = doctor;
        this.payment = payment;
    }

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

    public String getCuring() {
        return curing;
    }

    public void setCuring(String curing) {
        this.curing = curing;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Integer getNumberNights() {
        return numberNights;
    }

    public void setNumberNights(Integer numberNights) {
        this.numberNights = numberNights;
    }

    public boolean isPayment() {
        return payment;
    }

    public void setPayment(boolean payment) {
        this.payment = payment;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}
