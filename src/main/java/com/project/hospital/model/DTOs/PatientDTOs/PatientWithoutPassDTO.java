package com.project.hospital.model.DTOs.PatientDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientWithoutPassDTO {

    private long id;
    private String email;
    private String name;
    private String phone;
}
