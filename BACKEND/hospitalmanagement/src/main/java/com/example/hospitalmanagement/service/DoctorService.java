package com.example.hospitalmanagement.service;

import com.example.hospitalmanagement.dto.DoctorRequestDTO;
import com.example.hospitalmanagement.dto.DoctorResponseDTO;
import com.example.hospitalmanagement.model.Doctor;
import com.example.hospitalmanagement.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DoctorService {

    private final DoctorRepository repository;

    @Autowired
    public DoctorService(DoctorRepository repository) {
        this.repository = repository;
    }

    public Mono<DoctorResponseDTO> saveDoctor(DoctorRequestDTO dto) {
        Doctor doctor = new Doctor();
        doctor.setName(dto.name());
        doctor.setSpecialization(dto.specialization());
        doctor.setContact(dto.contact());

        return repository.save(doctor)
                .map(this::mapToResponse);
    }

    public Flux<DoctorResponseDTO> getAllDoctors() {
        return repository.findAll()
                .map(this::mapToResponse);
    }

    public Mono<DoctorResponseDTO> getDoctorById(String id) {
        return repository.findById(id)
                .map(this::mapToResponse);
    }

    public Mono<Void> deleteDoctor(String id) {
        return repository.deleteById(id);
    }

    private DoctorResponseDTO mapToResponse(Doctor doctor) {
        return new DoctorResponseDTO(
                doctor.getId(),
                doctor.getName(),
                doctor.getSpecialization(),
                doctor.getContact()
        );
    }

    public Mono<DoctorResponseDTO> updateDoctor(String id, DoctorRequestDTO dto) {
        return repository.findById(id)
                .flatMap(existing -> {
                    existing.setName(dto.name());
                    existing.setSpecialization(dto.specialization());
                    existing.setContact(dto.contact());
                    return repository.save(existing);
                })
                .map(this::mapToResponse);
    }

}