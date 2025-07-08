package com.example.hospitalmanagement.service;

import com.example.hospitalmanagement.dto.PatientRequestDTO;
import com.example.hospitalmanagement.model.Patient;
import com.example.hospitalmanagement.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class PatientServiceTest {

    private final PatientRepository repository = mock(PatientRepository.class);
    private final PatientService service = new PatientService(repository);

    @Test
    void testSavePatient() {
        // Arrange
        PatientRequestDTO dto = new PatientRequestDTO("Priya", 28, "Female", "9876543210");

        Patient saved = new Patient("1", "Priya", 28, "Female", "9876543210");

        // ✅ Use correct generic matcher
        @SuppressWarnings("unchecked")
        Mono<Patient> savedMono = Mono.just(saved);

        when(repository.save(any(Patient.class))).thenReturn(savedMono);

        // Act & Assert
        StepVerifier.create(service.savePatient(dto))
                .expectNextMatches(response ->
                        response.name().equals("Priya") &&
                                response.phone().equals("9876543210"))
                .verifyComplete();
    }
}