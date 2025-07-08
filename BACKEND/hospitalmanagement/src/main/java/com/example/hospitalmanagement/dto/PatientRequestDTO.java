package com.example.hospitalmanagement.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record PatientRequestDTO(
        @NotBlank(message = "Name is required")
        String name,

        @Min(value = 0, message = "Age must be positive")
        int age,

        @NotBlank(message = "Gender is required")
        String gender,

        @Pattern(regexp = "\\d{10}", message = "Phone must be 10 digits")
        String phone
) {}