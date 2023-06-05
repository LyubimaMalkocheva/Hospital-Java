package com.project.hospital.model.repositories;

import com.project.hospital.model.entities.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepository  extends JpaRepository<Hospital, Long> {
}
