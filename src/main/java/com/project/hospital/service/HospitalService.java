package com.project.hospital.service;

import com.project.hospital.model.entities.Hospital;
import com.project.hospital.model.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HospitalService extends AbstractService{

    public Hospital getHospital(){
        Optional<Hospital> optionalHospital = hospitalRepository.findById(1L);
        if (optionalHospital.isPresent()) {
            return optionalHospital.get();
        } else {
            throw new NotFoundException("Hospital not found");
        }
    }

    public Hospital updateHospital(Hospital hospital){
        return  hospitalRepository.save(hospital);
    }
}
