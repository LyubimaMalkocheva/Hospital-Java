package com.project.hospital.model.entities;

import com.project.hospital.TypeRoom;
import com.project.hospital.model.exceptions.NotFoundPatientInRoom;
import jakarta.persistence.*;

import java.util.Set;
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id; // number of room;
    @OneToMany(mappedBy = "room")
    private Set<Patient> patients;
    private Integer availableBeds;
    private TypeRoom typeRoom;

    @ManyToOne
    @JoinColumn(name = "department")
    private Department department;

    public Room() {
    }

    public Room(Set<Patient> patients, Integer availableBeds, TypeRoom typeRoom, Department department) {
        this.patients = patients;
        this.availableBeds = availableBeds;
        this.typeRoom = typeRoom;
        this.department = department;
    }

    public Department getDepartment() {
        return department;
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


    public void addPatientToRoom(Patient patient){
        if(this.availableBeds > 0){
               patients.add(patient); // patient take one available bed
               this.availableBeds--; // So number of beds will be reduced
        }
    }

    public void removePatientFromRoom(Patient patient) throws NotFoundPatientInRoom {
        if(this.patients.contains(patient)){
            patients.remove(patient); // patient leave one available bed
            this.availableBeds++; // So number of beds will be increased
        }else{
            throw new NotFoundPatientInRoom(patient + "is not in room number " + this.Id);
        }
    }
}
