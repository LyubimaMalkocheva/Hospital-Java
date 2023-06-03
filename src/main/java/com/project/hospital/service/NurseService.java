package com.project.hospital.service;

import com.project.hospital.model.entities.Nurse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NurseService extends AbstractService{
    public List<Nurse> getAllNurses() {
        return nurseRepository.findAll();
    }
    public Nurse getNurseById(Long id){
        return nurseRepository.findAll().stream().filter(t -> id.equals(t.getId()))
                .findFirst()
                .orElse(null);

    }

    public Nurse registerNewNurse(Nurse nurse) {
        return nurseRepository.save(nurse);
    }
    public Nurse updateNurse(Nurse nurse){
        return  nurseRepository.save(nurse);
    }

    public void deleteNurseById(Long id){
        boolean exists = nurseRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Nurse with id " + id + " does not exists");
        }else {
            nurseRepository.deleteById(id);
            System.out.println("Nurse with id - " + id + " is deleted!");
        }
    }
}
