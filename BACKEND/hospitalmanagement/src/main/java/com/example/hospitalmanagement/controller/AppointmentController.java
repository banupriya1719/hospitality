package com.example.hospitalmanagement.controller;

import com.example.hospitalmanagement.dto.AppointmentRequestDTO;
import com.example.hospitalmanagement.dto.AppointmentResponseDTO;
import com.example.hospitalmanagement.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    //  Create new appointment
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<AppointmentResponseDTO> createAppointment(@Valid @RequestBody AppointmentRequestDTO requestDTO) {
        return appointmentService.saveAppointment(requestDTO);
    }

    //  Get all appointments
    @GetMapping
    public Flux<AppointmentResponseDTO> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    //  Get appointment by ID
    @GetMapping("/{id}")
    public Mono<AppointmentResponseDTO> getAppointmentById(@PathVariable String id) {
        return appointmentService.getAppointmentById(id);
    }

    //  Delete appointment by ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteAppointment(@PathVariable String id) {
        return appointmentService.deleteAppointment(id);
    }
    //  Update appointment by ID
    @PutMapping("/{id}")
    public Mono<AppointmentResponseDTO> updateAppointment(@PathVariable String id, @Valid @RequestBody AppointmentRequestDTO dto) {
        return appointmentService.updateAppointment(id, dto);
    }

}