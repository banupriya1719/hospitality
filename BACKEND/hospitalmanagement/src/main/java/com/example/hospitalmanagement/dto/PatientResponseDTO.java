package com.example.hospitalmanagement.dto;
public record PatientResponseDTO(
        String id,
        String name,
        int age,
        String gender,
        String phone
) {}