package com.example.hospitalmanagement.dto;
public record MedicationResponseDTO(
        String id,
        String patientId,
        String name,
        String dosage,
        String instructions
) {}
