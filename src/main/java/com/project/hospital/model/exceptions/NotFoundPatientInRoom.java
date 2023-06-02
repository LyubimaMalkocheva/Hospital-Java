package com.project.hospital.model.exceptions;

public class NotFoundPatientInRoom extends Exception{
    public NotFoundPatientInRoom(String message) {
        super(message);
    }
}
