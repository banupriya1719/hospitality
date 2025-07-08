package com.example.hospitalmanagement.service;

import com.example.hospitalmanagement.dto.AppointmentRequestDTO;
import com.example.hospitalmanagement.dto.AppointmentResponseDTO;
import com.example.hospitalmanagement.model.Appointment;
import com.example.hospitalmanagement.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AppointmentService {

    private final AppointmentRepository repository;

    @Autowired
    public AppointmentService(AppointmentRepository repository) {
        this.repository = repository;
    }

    public Mono<AppointmentResponseDTO> saveAppointment(AppointmentRequestDTO dto) {
        Appointment appointment = new Appointment();
        appointment.setPatientId(dto.patientId());
        appointment.setDoctorId(dto.doctorId());
        appointment.setAppointmentDate(dto.appointmentDate());
        appointment.setReason(dto.reason());

        return repository.save(appointment)
                .map(this::mapToResponse);
    }

    public Flux<AppointmentResponseDTO> getAllAppointments() {
        return repository.findAll()
                .map(this::mapToResponse);
    }

    public Mono<AppointmentResponseDTO> getAppointmentById(String id) {
        return repository.findById(id)
                .map(this::mapToResponse);
    }

    public Mono<Void> deleteAppointment(String id) {
        return repository.deleteById(id);
    }

    private AppointmentResponseDTO mapToResponse(Appointment a) {
        return new AppointmentResponseDTO(
                a.getId(),
                a.getPatientId(),
                a.getDoctorId(),
                a.getAppointmentDate(),
                a.getReason()
        );
    }

    public Mono<AppointmentResponseDTO> updateAppointment(String id, AppointmentRequestDTO dto) {
        return repository.findById(id)
                .flatMap(existing -> {
                    existing.setPatientId(dto.patientId());
                    existing.setDoctorId(dto.doctorId());
                    existing.setAppointmentDate(dto.appointmentDate());
                    existing.setReason(dto.reason());
                    return repository.save(existing);
                })
                .map(this::mapToResponse);
    }

}