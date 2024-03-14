package com.LAB.LabAppointmentSystem.service;

import com.LAB.LabAppointmentSystem.model.Patient;

public interface PatientService {
    Patient findByNicNumber(String nicNumber);
    Patient save(Patient patient);
    boolean authenticate(String nicNumber, String password);
}