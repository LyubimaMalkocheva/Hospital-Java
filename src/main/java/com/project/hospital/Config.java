package com.project.hospital;

import com.project.hospital.model.entities.*;
import com.project.hospital.model.exceptions.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class Config {
    @Bean
    CommandLineRunner commandLineRunner(DepartmentRepository departmentRepository, DoctorRepository doctorRepository,
                                        HospitalRepository  hospitalRepository,NurseRepository nurseRepository,
                                        PatientRepository patientRepository, RoomRepository roomRepository,
                                        StaffRepository staffRepository){


        return args -> {
        //TODO add preload table configuration
        //Hospital
            Hospital hospital = new Hospital("Hospital Tokuda", "ul. Ivan Shishman 135");
            hospitalRepository.save(hospital);

        //Departments
            Department surgery = new Department(hospital, "surgery");
            Department paediatrics = new Department(hospital, "paediatrics");
            departmentRepository.saveAll(List.of(surgery, paediatrics));

        //Rooms
            Room paediatricRoom1 = new Room(3,TypeRoom.NIGHT_ROOM, paediatrics);
            Room paediatricRoom2 = new Room(2,TypeRoom.NIGHT_ROOM, paediatrics);
            Room surgeryRoom1 = new Room(1,TypeRoom.OPERATING_ROOM, surgery);
            Room surgeryRoom2 = new Room(1,TypeRoom.OPERATING_ROOM, surgery);

            roomRepository.saveAll(List.of(paediatricRoom1,paediatricRoom2,surgeryRoom1,surgeryRoom2));

        //Doctors
            Doctor surgeon1 = new Doctor("Ivan Ivanov", "0877000078","ivan.ivanov@maildrop.cc",
                    "thisIsMyInitialPassword1",surgery,true,Qualification.Surgeon);
            Doctor surgeon2 = new Doctor("Georgi Georgiev", "0877003478","georgi.georgiev@maildrop.cc",
                    "thisIsMyInitialPassword1",surgery,false,Qualification.Surgeon);
            Doctor paediatric1 = new Doctor("Petrunka Petrova", "0875124432","petrunka.petrova@maildrop.cc",
                    "thisIsMyInitialPassword1", paediatrics,true);
            Doctor paediatric2 = new Doctor("Olivia Shanae", "0875125555","olivia.shanae@maildrop.cc",
                    "thisIsMyInitialPassword1", paediatrics,false, Qualification.Pediatrician);

            doctorRepository.saveAll(List.of(surgeon1,surgeon2, paediatric1,paediatric2));

        //Nurses
            Nurse nurse1 = new Nurse("Sophia Johnson","0812345678","sophia.johnson@maildrop.cc",
                    "thisIsMyInitialPassword1",surgery);
            Nurse nurse2 = new Nurse("Andrew Peterson","0843210987","andrew.peterson@maildrop.cc",
                    "thisIsMyInitialPassword1",surgery);
            Nurse nurse3 = new Nurse("Natalie Powell","0898765432","natalie.powell@maildrop.cc",
                    "thisIsMyInitialPassword1",paediatrics);
            Nurse nurse4 = new Nurse("Aubrey Price","0876543210","aubrey.price@maildrop.cc",
                    "thisIsMyInitialPassword1",paediatrics);
            Nurse nurse5 = new Nurse("Addison Diaz","0823456789","addison.diaz@maildrop.cc",
                    "thisIsMyInitialPassword1",paediatrics);

            nurseRepository.saveAll(List.of(nurse1,nurse2,nurse3,nurse4,nurse5));

        };
    }
}
