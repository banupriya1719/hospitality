package com.example.hospitalmanagement.dto;

import java.time.LocalDateTime;

public record AppointmentResponseDTO(
        String id,
        String patientId,
        String doctorId,
        LocalDateTime appointmentDate,
        String reason
) {}