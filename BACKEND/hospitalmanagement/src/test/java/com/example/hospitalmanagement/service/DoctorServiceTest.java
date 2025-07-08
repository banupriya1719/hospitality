package com.example.hospitalmanagement.service;

import com.example.hospitalmanagement.dto.DoctorRequestDTO;
import com.example.hospitalmanagement.model.Doctor;
import com.example.hospitalmanagement.repository.DoctorRepository;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;

public class DoctorServiceTest {

    private final DoctorRepository repository = mock(DoctorRepository.class);
    private final DoctorService service = new DoctorService(repository);

    @Test
    void testSaveDoctor() {
        DoctorRequestDTO dto = new DoctorRequestDTO("Dr. Raji", "Pediatrics", "9000011111");

        Doctor saved = new Doctor("1", "Dr. Raji", "Pediatrics", "9000011111");

        when(repository.save(any(Doctor.class))).thenReturn(Mono.just(saved));

        StepVerifier.create(service.saveDoctor(dto))
                .expectNextMatches(res ->
                        res.name().equals("Dr. Raji") &&
                                res.contact().equals("9000011111"))
                .verifyComplete();
    }
}
