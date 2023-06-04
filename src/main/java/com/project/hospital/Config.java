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

        //Staff
            Staff staff1 = new Staff("John Smith","0813579246","john.smith@maildrop.cc",
                    "thisIsMyInitialPassword1",hospital);
            Staff staff2 = new Staff("Emily Johnson","0834567890","emily.johnson@maildrop.cc",
                    "thisIsMyInitialPassword1",hospital);
            Staff staff3 = new Staff("Michael Davis","0832109876","michael.davis@maildrop.cc",
                    "thisIsMyInitialPassword1",hospital);
            Staff staff4 = new Staff("Sarah Anderson","0812345670","sarah.anderson@maildrop.cc",
                    "thisIsMyInitialPassword1",hospital);
            Staff staff5 = new Staff("Christopher Wilson","0856789012","christopher.wilson@maildrop.cc",
                    "thisIsMyInitialPassword1",hospital);
            staffRepository.saveAll(List.of(staff1,staff2,staff3,staff4,staff5));

        //Patients
            Patient patient1 = new Patient("Scarlett King","0854321098","scarlett.king@maildrop.cc",
                    "thisIsMyInitialPassword1");
            Patient patient2 = new Patient("Oliver White", "0812345678", "oliver.white@maildrop.cc",
                    "thisIsMyInitialPassword2");
            Patient patient3 = new Patient("Emma Brown", "0823456789", "emma.brown@maildrop.cc",
                    "thisIsMyInitialPassword3");
            Patient patient4 = new Patient("Henry Green", "0834567890", "henry.green@maildrop.cc",
                    "thisIsMyInitialPassword4");
            Patient patient5 = new Patient("Isabella Gray", "0845678901", "isabella.gray@maildrop.cc",
                    "thisIsMyInitialPassword5");
            Patient patient6 = new Patient("William Black", "0856789012", "william.black@maildrop.cc",
                    "thisIsMyInitialPassword6");

            patientRepository.saveAll(List.of(patient1,patient2,patient3,patient4,patient5,patient6));
        };
    }
}
