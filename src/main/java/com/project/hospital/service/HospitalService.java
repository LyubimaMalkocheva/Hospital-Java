package com.project.hospital.service;

import com.project.hospital.model.entities.Hospital;
import org.springframework.stereotype.Service;

@Service
public class HospitalService extends AbstractService{

    public Hospital getHospital(){
        return hospitalRepository.findById(Long.valueOf(1)).orElse(null);
    }
    public Hospital updateHospital(Hospital hospital){
        return  hospitalRepository.save(hospital);
    }
}
