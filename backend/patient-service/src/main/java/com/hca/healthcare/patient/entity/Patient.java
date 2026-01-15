package com.hca.healthcare.patient.entity;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Table("patients")
public class Patient {
    @PrimaryKey
    private UUID patientId;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String email;
    private String phone;
    private String address;
    private String bloodGroup;
    private String medicalHistory;
    private LocalDate registrationDate;
}