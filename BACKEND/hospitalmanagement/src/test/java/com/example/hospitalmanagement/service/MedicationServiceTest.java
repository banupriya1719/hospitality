package com.example.hospitalmanagement.service;

import com.example.hospitalmanagement.dto.MedicationRequestDTO;
import com.example.hospitalmanagement.model.Medication;
import com.example.hospitalmanagement.repository.MedicationRepository;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;

public class MedicationServiceTest {

    private final MedicationRepository repository = mock(MedicationRepository.class);
    private final MedicationService service = new MedicationService(repository);

    @Test
    void testSaveMedication() {
        MedicationRequestDTO dto = new MedicationRequestDTO("patient1", "Paracetamol", "500mg", "2 times a day");

        Medication saved = new Medication("1", "patient1", "Paracetamol", "500mg", "2 times a day");

        when(repository.save(any(Medication.class))).thenReturn(Mono.just(saved));

        StepVerifier.create(service.saveMedication(dto))
                .expectNextMatches(res ->
                        res.name().equals("Paracetamol") &&
                                res.dosage().equals("500mg"))
                .verifyComplete();
    }
}
