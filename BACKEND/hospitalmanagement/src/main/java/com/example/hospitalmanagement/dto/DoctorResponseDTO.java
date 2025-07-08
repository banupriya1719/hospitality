package com.example.hospitalmanagement.dto;
public record DoctorResponseDTO(
        String id,
        String name,
        String specialization,
        String contact
) {}