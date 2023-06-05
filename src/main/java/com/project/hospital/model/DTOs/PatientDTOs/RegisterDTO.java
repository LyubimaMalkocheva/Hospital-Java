package com.project.hospital.model.DTOs.PatientDTOs;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {

    @NotNull(message = "Email cannot be null!")
    @Email(message = "Invalid email!", regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
    private String email;

    @NotNull(message = "Password cannot be null!")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$", message = "Invalid password!")
    private String password;

    @NotNull(message = "Confirm password cannot be null!")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$", message = "Invalid confirm password!")
    private String confirmPassword;


    @NotBlank(message = "Name cannot be blank!")
    @Size(max = 15, message = "Name cannot be longer than 15 characters")
    private String firstName;

    @NotBlank(message = "Phone number cannot be blank!")
    @Pattern(regexp = "^08\\d{8}$", message = "Phone number must start with '08' followed by 8 digits")
    private String phoneNumber;
}
