package com.project.hospital.service;

import com.project.hospital.model.entities.Patient;
import com.project.hospital.model.exceptions.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    private final PatientRepository patientRepository;
    @Autowired
    public PatientService(PatientRepository patientRepository){this.patientRepository=patientRepository;}
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }
    public Patient getPatientById(Long id){
        return patientRepository.findAll().stream().filter(t -> id.equals(t.getId()))
                .findFirst()
                .orElse(null);

    }

    public Patient registerNewPatient(Patient patient) {
        return patientRepository.save(patient);
    }
    public Patient updatePatient(Patient patient){
        return  patientRepository.save(patient);
    }

    public void deletePatientById(Long id){
        boolean exists = patientRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Patient with id " + id + " does not exists");
        }
        patientRepository.deleteById(id);
        System.out.println("Patient with id - " + id +" is deleted!");
    }
}
