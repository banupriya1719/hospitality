package com.example.hospitalmanagement.service;

import com.example.hospitalmanagement.dto.AppointmentRequestDTO;
import com.example.hospitalmanagement.model.Appointment;
import com.example.hospitalmanagement.repository.AppointmentRepository;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;

public class AppointmentServiceTest {

    private final AppointmentRepository repository = mock(AppointmentRepository.class);
    private final AppointmentService service = new AppointmentService(repository);

    @Test
    void testSaveAppointment() {
        LocalDateTime date = LocalDateTime.now();

        AppointmentRequestDTO dto = new AppointmentRequestDTO("patient1", "doctor1", date, "Check-up");
        Appointment saved = new Appointment("1", "patient1", "doctor1", date, "Check-up");

        when(repository.save(any(Appointment.class))).thenReturn(Mono.just(saved));

        StepVerifier.create(service.saveAppointment(dto))
                .expectNextMatches(res ->
                        res.patientId().equals("patient1") &&
                                res.reason().equals("Check-up"))
                .verifyComplete();
    }
}
