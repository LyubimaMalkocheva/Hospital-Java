package com.project.hospital;

import com.project.hospital.model.exceptions.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    CommandLineRunner commandLineRunner(DoctorRepository doctorRepository, NurseRepository nurseRepository,
                                        PatientRepository patientRepository, RoomRepository roomRepository,
                                        StaffRepository staffRepository){


        return args -> {
        //TODO add preload table configuration
        };
    }
}
