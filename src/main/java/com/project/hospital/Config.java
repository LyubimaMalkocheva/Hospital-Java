package com.project.hospital;

import com.project.hospital.model.entities.Doctor;
import com.project.hospital.model.repositories.DoctorRepository;
import com.project.hospital.model.repositories.NurseRepository;
import com.project.hospital.model.repositories.PatientRepository;
import com.project.hospital.model.repositories.RoomRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    CommandLineRunner commandLineRunner(DoctorRepository doctorRepository, NurseRepository nurseRepository,
                                        PatientRepository patientRepository, RoomRepository roomRepository){


        return args -> {
        //TODO add preload table configuration
        };
    }
}
