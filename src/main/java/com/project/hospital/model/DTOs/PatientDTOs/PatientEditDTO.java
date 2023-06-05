package com.project.hospital.model.DTOs.PatientDTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientEditDTO {

    @Size(max = 15, message = "Name cannot be longer than 15 characters")
    @NotBlank(message = "Name cannot be blank!")
    private String name;

    @NotBlank(message = "Phone number cannot be blank!")
    @Pattern(regexp = "^08\\d{8}$", message = "Phone number must start with '08' followed by 8 digits")
    private String phoneNumber;
}
