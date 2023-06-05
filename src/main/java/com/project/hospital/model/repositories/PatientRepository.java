package com.project.hospital.model.repositories;

import com.project.hospital.model.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {

    Optional<Patient> findByEmail(String email);
    boolean existsByEmail(String email);

    Optional<Patient> findById(long id);

    Optional<Patient> findByUniqueCodeAndExpirationDateBefore(String code, LocalDateTime localDateTime);
}
