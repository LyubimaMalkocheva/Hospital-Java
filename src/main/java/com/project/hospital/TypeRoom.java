package com.project.hospital;

import com.project.hospital.model.entities.Room;

public enum TypeRoom {
    OPERATING_ROOM(100.), INTENSIVECARE_UNIT(120.0), NIGHT_ROOM(55.0);

    private Double roomPrice;

    TypeRoom(Double roomPrice) {
        this.roomPrice = roomPrice;
    }

    public Double getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(Double roomPrice) {
        this.roomPrice = roomPrice;
    }
}

