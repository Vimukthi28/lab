package com.LAB.LabAppointmentSystem.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String nicNumber;
    private String contactDetails;
    private String password;
    private int failedLoginAttempts;
}
