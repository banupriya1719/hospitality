package com.example.hospitalmanagement.service;

import com.example.hospitalmanagement.dto.MedicationRequestDTO;
import com.example.hospitalmanagement.dto.MedicationResponseDTO;
import com.example.hospitalmanagement.model.Medication;
import com.example.hospitalmanagement.repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MedicationService {

    private final MedicationRepository repository;

    @Autowired
    public MedicationService(MedicationRepository repository) {
        this.repository = repository;
    }

    public Mono<MedicationResponseDTO> saveMedication(MedicationRequestDTO dto) {
        Medication medication = new Medication();
        medication.setPatientId(dto.patientId());
        medication.setName(dto.name());
        medication.setDosage(dto.dosage());
        medication.setInstructions(dto.instructions());

        return repository.save(medication)
                .map(this::mapToResponse);
    }

    public Flux<MedicationResponseDTO> getAllMedications() {
        return repository.findAll()
                .map(this::mapToResponse);
    }

    public Mono<MedicationResponseDTO> getMedicationById(String id) {
        return repository.findById(id)
                .map(this::mapToResponse);
    }

    public Mono<Void> deleteMedication(String id) {
        return repository.deleteById(id);
    }

    private MedicationResponseDTO mapToResponse(Medication m) {
        return new MedicationResponseDTO(
                m.getId(),
                m.getPatientId(),
                m.getName(),
                m.getDosage(),
                m.getInstructions()
        );
    }

    public Mono<MedicationResponseDTO> updateMedication(String id, MedicationRequestDTO dto) {
        return repository.findById(id)
                .flatMap(existing -> {
                    existing.setPatientId(dto.patientId());
                    existing.setName(dto.name());
                    existing.setDosage(dto.dosage());
                    existing.setInstructions(dto.instructions());
                    return repository.save(existing);
                })
                .map(this::mapToResponse);
    }

}
