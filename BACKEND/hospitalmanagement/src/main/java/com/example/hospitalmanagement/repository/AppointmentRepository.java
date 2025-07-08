package com.example.hospitalmanagement.repository;

import com.example.hospitalmanagement.model.Appointment;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface AppointmentRepository extends ReactiveMongoRepository<Appointment, String> {
}