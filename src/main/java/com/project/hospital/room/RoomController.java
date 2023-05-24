package com.project.hospital.room;

import com.project.hospital.doctor.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/room")
public class RoomController {
    @Autowired
    private RoomService roomService;
}
