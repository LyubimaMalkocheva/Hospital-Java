package com.project.hospital.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@MappedSuperclass
@Getter
@Setter
public abstract class Personal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column
    @NotBlank(message = "Name should not be blank")
    protected String name = null;

    @Column
    @Pattern(regexp = "^08\\d{8}$", message = "Phone number must start with '08' followed by 8 digits")
    protected String phone;

    @Column
    protected String email;

    @Column
    protected String password;


    protected Personal() {
    }

    public Personal(String name, String phone, String email, String password) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public Personal(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Personal personal = (Personal) o;
        return id.equals(personal.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
