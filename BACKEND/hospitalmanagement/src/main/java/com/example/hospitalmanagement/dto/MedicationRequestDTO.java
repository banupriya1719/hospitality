package com.example.hospitalmanagement.dto;

import jakarta.validation.constraints.NotBlank;

public record MedicationRequestDTO(
        @NotBlank(message = "Patient ID is required")
        String patientId,

        @NotBlank(message = "Medicine name is required")
        String name,

        @NotBlank(message = "Dosage is required")
        String dosage,

        @NotBlank(message = "Instructions are required")
        String instructions
) {}