package com.LAB.LabAppointmentSystem.service;

import com.LAB.LabAppointmentSystem.model.Patient;
import com.LAB.LabAppointmentSystem.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public Patient findByNicNumber(String nicNumber) {
        return patientRepository.findByNicNumber(nicNumber);
    }

    @Override
    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public boolean authenticate(String nicNumber, String password) {
        // Retrieve patient by NIC Number from database
        Patient patient = patientRepository.findByNicNumber(nicNumber);

        // Check if patient exists
        if (patient != null) {
            // Check if the account is locked due to too many failed attempts
            if (patient.getFailedLoginAttempts() >= 3) {
                // You can implement further actions here, such as locking the account or sending a notification
                return false; // Authentication failed
            }

            // Check if password matches
            if (patient.getPassword().equals(password)) {
                // Reset failed login attempts
                patient.setFailedLoginAttempts(0);
                patientRepository.save(patient);
                return true; // Authentication successful
            } else {
                // Increment failed login attempts
                int failedAttempts = patient.getFailedLoginAttempts() + 1;
                patient.setFailedLoginAttempts(failedAttempts);
                patientRepository.save(patient);
                return false;// Password is incorrect
            }
        } else {
            // Patient not found in the database
            System.out.println("Patient with NIC Number " + nicNumber + " not found.");
            return false; // Authentication failed
        }
    }
}
