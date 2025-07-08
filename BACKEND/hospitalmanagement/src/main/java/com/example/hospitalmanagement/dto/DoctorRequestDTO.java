package com.example.hospitalmanagement.dto;

import jakarta.validation.constraints.NotBlank;

public record DoctorRequestDTO(
        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Specialization is required")
        String specialization,

        @NotBlank(message = "Contact is required")
        String contact
) {}