package com.LAB.LabAppointmentSystem.controller;

import com.LAB.LabAppointmentSystem.model.Patient;
import com.LAB.LabAppointmentSystem.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String nicNumber, @RequestParam String password, Model model) {
        if (patientService.authenticate(nicNumber, password)) {
            // Redirect to dashboard or home page
            return "redirect:/dashboard";
        } else {
            // Return login page with error message

            model.addAttribute("error", "Invalid credentials");
            return "login";
        }
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute @Valid Patient patient, BindingResult bindingResult, Model model) {
        // Perform data validation
        if (bindingResult.hasErrors()) {
            // If there are validation errors, return to the registration form with error messages
            return "register";
        }

        // Save patient to database
        patientService.save(patient);

        // Redirect to the login page after successful registration
        return "redirect:/login";
    }
}
