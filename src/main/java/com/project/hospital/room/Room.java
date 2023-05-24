package com.project.hospital.room;

import com.project.hospital.patient.Patient;
import jakarta.persistence.*;

import java.util.Set;
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;// number of room;
    @OneToMany(mappedBy = "room")
    private Set<Patient> patients;
    private Integer availableBeds;
    private TypeRoom typeRoom;

    public Room() {
    }

    public Room(Long id, Set<Patient> patients, Integer availableBeds, TypeRoom typeRoom) {
        Id = id;
        this.patients = patients;
        this.availableBeds = availableBeds;
        this.typeRoom = typeRoom;
    }

    public Room(Integer availableBeds, TypeRoom typeRoom) {
        this.availableBeds = availableBeds;
        this.typeRoom = typeRoom;
    }

    public Room(Set<Patient> patients, Integer availableBeds, TypeRoom typeRoom) {
        this.patients = patients;
        this.availableBeds = availableBeds;
        this.typeRoom = typeRoom;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }

    public Integer getAvailableBeds() {
        return availableBeds;
    }

    public void setAvailableBeds(Integer availableBeds) {
        this.availableBeds = availableBeds;
    }

    public TypeRoom getTypeRoom() {
        return typeRoom;
    }

    public void setTypeRoom(TypeRoom typeRoom) {
        this.typeRoom = typeRoom;
    }
}
