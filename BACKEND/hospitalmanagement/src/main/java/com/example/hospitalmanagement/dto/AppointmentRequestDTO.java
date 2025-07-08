package com.example.hospitalmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AppointmentRequestDTO(
        @NotBlank(message = "Patient ID is required")
        String patientId,

        @NotBlank(message = "Doctor ID is required")
        String doctorId,

        @NotNull(message = "Appointment date is required")
        LocalDateTime appointmentDate,

        @NotBlank(message = "Reason is required")
        String reason
) {}