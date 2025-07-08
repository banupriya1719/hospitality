package com.example.hospitalmanagement.controller;

import com.example.hospitalmanagement.dto.DoctorRequestDTO;
import com.example.hospitalmanagement.dto.DoctorResponseDTO;
import com.example.hospitalmanagement.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    //  Create a new doctor
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<DoctorResponseDTO> createDoctor(@Valid @RequestBody DoctorRequestDTO requestDTO) {
        return doctorService.saveDoctor(requestDTO);
    }

    //  Get all doctors
    @GetMapping
    public Flux<DoctorResponseDTO> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    //  Get doctor by ID
    @GetMapping("/{id}")
    public Mono<DoctorResponseDTO> getDoctorById(@PathVariable String id) {
        return doctorService.getDoctorById(id);
    }

    //  Delete doctor by ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteDoctor(@PathVariable String id) {
        return doctorService.deleteDoctor(id);
    }
    //  Updating doctor by ID
    @PutMapping("/{id}")
    public Mono<DoctorResponseDTO> updateDoctor(@PathVariable String id, @Valid @RequestBody DoctorRequestDTO dto) {
        return doctorService.updateDoctor(id, dto);
    }

}