package com.example.hospitalmanagement.controller;

import com.example.hospitalmanagement.dto.PatientRequestDTO;
import com.example.hospitalmanagement.dto.PatientResponseDTO;
import com.example.hospitalmanagement.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    //  Create new patient
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<PatientResponseDTO> createPatient(@Valid @RequestBody PatientRequestDTO requestDTO) {
        return patientService.savePatient(requestDTO);
    }

    //  Get all patients
    @GetMapping
    public Flux<PatientResponseDTO> getAllPatients() {
        return patientService.getAllPatients();
    }

    //  Get patient by ID
    @GetMapping("/{id}")
    public Mono<PatientResponseDTO> getPatientById(@PathVariable String id) {
        return patientService.getPatientById(id);
    }

    //  Delete patient by ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deletePatient(@PathVariable String id) {
        return patientService.deletePatient(id);
    }
    //  Updating patient by ID
    @PutMapping("/{id}")
    public Mono<PatientResponseDTO> updatePatient(@PathVariable String id, @Valid @RequestBody PatientRequestDTO dto) {
        return patientService.updatePatient(id, dto);
    }

}