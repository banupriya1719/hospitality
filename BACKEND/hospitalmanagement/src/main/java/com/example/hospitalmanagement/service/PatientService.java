package com.example.hospitalmanagement.service;

import com.example.hospitalmanagement.dto.PatientRequestDTO;
import com.example.hospitalmanagement.dto.PatientResponseDTO;
import com.example.hospitalmanagement.model.Patient;
import com.example.hospitalmanagement.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PatientService {

    private final PatientRepository repository;

    @Autowired
    public PatientService(PatientRepository repository) {
        this.repository = repository;
    }

    public Mono<PatientResponseDTO> savePatient(PatientRequestDTO dto) {
        Patient patient = new Patient();
        patient.setName(dto.name());
        patient.setAge(dto.age());
        patient.setGender(dto.gender());
        patient.setPhone(dto.phone());

        return repository.save(patient)
                .map(this::mapToResponse);
    }

    public Flux<PatientResponseDTO> getAllPatients() {
        return repository.findAll()
                .map(this::mapToResponse);
    }

    public Mono<PatientResponseDTO> getPatientById(String id) {
        return repository.findById(id)
                .map(this::mapToResponse);
    }

    public Mono<Void> deletePatient(String id) {
        return repository.deleteById(id);
    }

    private PatientResponseDTO mapToResponse(Patient patient) {
        return new PatientResponseDTO(
                patient.getId(),
                patient.getName(),
                patient.getAge(),
                patient.getGender(),
                patient.getPhone()
        );
    }

    public Mono<PatientResponseDTO> updatePatient(String id, PatientRequestDTO dto) {
        return repository.findById(id)
                .flatMap(existing -> {
                    existing.setName(dto.name());
                    existing.setAge(dto.age());
                    existing.setGender(dto.gender());
                    existing.setPhone(dto.phone());
                    return repository.save(existing);
                })
                .map(this::mapToResponse);
    }
}