package com.example.hospitalmanagement.repository;

import com.example.hospitalmanagement.model.Medication;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MedicationRepository extends ReactiveMongoRepository<Medication, String> {
}