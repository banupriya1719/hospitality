package com.example.hospitalmanagement.repository;

import com.example.hospitalmanagement.model.Doctor;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface DoctorRepository extends ReactiveMongoRepository<Doctor, String> {
}