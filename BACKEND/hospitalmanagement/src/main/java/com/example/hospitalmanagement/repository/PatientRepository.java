package com.example.hospitalmanagement.repository;

import com.example.hospitalmanagement.model.Patient;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PatientRepository extends ReactiveMongoRepository<Patient, String> {
}