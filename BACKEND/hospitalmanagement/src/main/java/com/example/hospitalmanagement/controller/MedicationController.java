package com.example.hospitalmanagement.controller;

import com.example.hospitalmanagement.dto.MedicationRequestDTO;
import com.example.hospitalmanagement.dto.MedicationResponseDTO;
import com.example.hospitalmanagement.service.MedicationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/medications")
public class MedicationController {

    private final MedicationService medicationService;

    @Autowired
    public MedicationController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    //  Create new medication record
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<MedicationResponseDTO> createMedication(@Valid @RequestBody MedicationRequestDTO requestDTO) {
        return medicationService.saveMedication(requestDTO);
    }

    //  Get all medications
    @GetMapping
    public Flux<MedicationResponseDTO> getAllMedications() {
        return medicationService.getAllMedications();
    }

    //  Get medication by ID
    @GetMapping("/{id}")
    public Mono<MedicationResponseDTO> getMedicationById(@PathVariable String id) {
        return medicationService.getMedicationById(id);
    }

    //  Delete medication by ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteMedication(@PathVariable String id) {
        return medicationService.deleteMedication(id);
    }
    //  Updating medication by ID
    @PutMapping("/{id}")
    public Mono<MedicationResponseDTO> updateMedication(@PathVariable String id, @Valid @RequestBody MedicationRequestDTO dto) {
        return medicationService.updateMedication(id, dto);
    }

}