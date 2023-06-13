package com.project.hospital.service;

import com.project.hospital.model.DTOs.PatientDTOs.*;
import com.project.hospital.model.entities.Patient;
import com.project.hospital.model.exceptions.BadRequestException;
import com.project.hospital.model.exceptions.NotFoundException;
import com.project.hospital.model.exceptions.UnauthorizedException;
import com.project.hospital.model.repositories.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PatientService extends AbstractService {
    private final PatientRepository patientRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private JavaMailSender javaMailSender;
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

    public PatientWithoutPassDTO register(RegisterDTO dto) {
            checkMatchingPasswords(dto.getPassword(), dto.getConfirmPassword());
        if(patientRepository.existsByEmail(dto.getEmail())){
            throw new BadRequestException("This email already  exists.");
        }
        Patient patient = new Patient();
        patient.setEmail(dto.getEmail());
        patient.setPhone(dto.getPhoneNumber());
        patient.setName(dto.getFirstName());
        patient.setPassword(encoder.encode(dto.getPassword()));
        String uniqueCode = UUID.randomUUID().toString();
        patient.setUniqueCode(uniqueCode);
        patientRepository.save(patient);
        new Thread(() -> {
            sendEmailValidation(patient.getEmail(), uniqueCode);
        }).start();

        PatientWithoutPassDTO patientWithoutPassDTO = new PatientWithoutPassDTO();
        patientWithoutPassDTO.setName(patient.getName());
        patientWithoutPassDTO.setId(patient.getId());
        patientWithoutPassDTO.setPhone(patient.getPhone());
        patientWithoutPassDTO.setEmail(patient.getEmail());

        return patientWithoutPassDTO;
    }

    public PatientWithoutPassDTO deletePatientById(int id, int loggedPatientId) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        Patient patient = verifyPatientExistence(optionalPatient);
        checkAuthorization(id, loggedPatientId);
        patientRepository.deleteById((long) id);

        PatientWithoutPassDTO patientWithoutPassDTO = new PatientWithoutPassDTO();
        patientWithoutPassDTO.setName(patient.getName());
        patientWithoutPassDTO.setId(patient.getId());
        patientWithoutPassDTO.setPhone(patient.getPhone());
        patientWithoutPassDTO.setEmail(patient.getEmail());

        return patientWithoutPassDTO;
    }

    @Transactional
    public PatientWithoutPassDTO login(LoginDTO dto) {
        Optional<Patient> optionalPatient = patientRepository.findByEmail(dto.getEmail());
        Patient patient = verifyPatientExistence(optionalPatient);
        checkCorrectCredentials(dto.getPassword(), patient.getPassword());
        if (!patient.isVerified()) {
            throw new UnauthorizedException("The patient is not verified. Please check your email.");
        }

        PatientWithoutPassDTO patientWithoutPassDTO = new PatientWithoutPassDTO();
        patientWithoutPassDTO.setName(patient.getName());
        patientWithoutPassDTO.setId(patient.getId());
        patientWithoutPassDTO.setPhone(patient.getPhone());
        patientWithoutPassDTO.setEmail(patient.getEmail());

        return patientWithoutPassDTO;
    }

    public PatientWithoutPassDTO updatePatientById(int id, PatientEditDTO editDto, int loggedUserId) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        checkAuthorization(id, loggedUserId);
        Patient patient = verifyPatientExistence(optionalPatient);
        patient.setName(editDto.getName());
        patient.setPhone(editDto.getPhoneNumber());
        patientRepository.save(patient);

        PatientWithoutPassDTO patientWithoutPassDTO = new PatientWithoutPassDTO();
        patientWithoutPassDTO.setName(patient.getName());
        patientWithoutPassDTO.setId(patient.getId());
        patientWithoutPassDTO.setPhone(patient.getPhone());
        patientWithoutPassDTO.setEmail(patient.getEmail());

        return patientWithoutPassDTO;
    }

    private void checkMatchingPasswords(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            throw new BadRequestException("The passwords do not match!");
        }
    }

    @SneakyThrows
    private void sendEmailValidation(String email, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("hospital.project.cscb@gmail.com");
        message.setTo(email);
        message.setSubject("Email Validation");
        message.setText("""
                Hi,
                                
                Please click the following link to validate your email: http://localhost:8081/email-validation?code=""" + code + """
                                
                Best regards,
                Hospital team""");
        javaMailSender.send(message);
    }

    private void checkCorrectCredentials(String password, String password1) {
        if (!encoder.matches(password, password1)) {
            throw new UnauthorizedException("Incorrect credentials.");
        }
    }

    private void checkAuthorization(int id, int loggedPatientId) {
        if (id != loggedPatientId) {
            throw new UnauthorizedException("You are not authorized to perform this action.");
        }
    }

    private Patient verifyPatientExistence(Optional<Patient> optionalPatient) {
        if (!optionalPatient.isPresent()) {
            throw new NotFoundException("Patient not found.");
        }

        return optionalPatient.get();
    }


    public PatientWithoutPassDTO validateCode(String code) {
        Patient patient = patientRepository.findByUniqueCodeAndExpirationDateBefore(code, LocalDateTime.now())
                .orElseThrow(() -> new BadRequestException("Validation code not found or has expired."));

        patient.setVerified(true);
        patientRepository.save(patient);

        PatientWithoutPassDTO patientWithoutPassDTO = new PatientWithoutPassDTO();
        patientWithoutPassDTO.setName(patient.getName());
        patientWithoutPassDTO.setId(patient.getId());
        patientWithoutPassDTO.setPhone(patient.getPhone());
        patientWithoutPassDTO.setEmail(patient.getEmail());

        return patientWithoutPassDTO;
    }

    public PatientWithoutPassDTO changePassword(int id, PatientPasswordChangeDTO passwordChangeDTO, int loggedPatientId) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        Patient patient = verifyPatientExistence(optionalPatient);
        checkAuthorization(id, loggedPatientId);
        checkCorrectCredentials(passwordChangeDTO.getPassword(), patient.getPassword());
        checkMatchingPasswords(passwordChangeDTO.getNewPassword(), passwordChangeDTO.getConfirmPassword());
        patient.setPassword(encoder.encode(passwordChangeDTO.getNewPassword()));
        patientRepository.save(patient);

        PatientWithoutPassDTO patientWithoutPassDTO = new PatientWithoutPassDTO();
        patientWithoutPassDTO.setName(patient.getName());
        patientWithoutPassDTO.setId(patient.getId());
        patientWithoutPassDTO.setPhone(patient.getPhone());
        patientWithoutPassDTO.setEmail(patient.getEmail());

        return patientWithoutPassDTO;
    }
}
