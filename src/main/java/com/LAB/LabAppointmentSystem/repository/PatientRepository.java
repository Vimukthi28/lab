package com.LAB.LabAppointmentSystem.repository;

import com.LAB.LabAppointmentSystem.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByNicNumber(String nicNumber);
}