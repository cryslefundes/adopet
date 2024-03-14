package br.com.cryslefundes.adopet.api.service.validation;

import br.com.cryslefundes.adopet.api.core.dto.adoption.RequestedAdoptionDTO;
import br.com.cryslefundes.adopet.api.core.enums.AdoptionStatus;
import br.com.cryslefundes.adopet.api.repository.AdoptionRepository;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TutorWithAdoptionLimitValidationTest {
    @InjectMocks
    private TutorWithAdoptionLimitValidation tutorValidation;
    @Mock
    private AdoptionRepository repository;

    @Test
    @DisplayName("Should validate when a tutor doesnt reached adoption limit")
    void shouldValidateTutorDoesNotReachedAdoptionLimit() {
        var dto = new RequestedAdoptionDTO(UUID.randomUUID(), UUID.randomUUID(), "any");
        when(repository.countByStatusAndTutorId(AdoptionStatus.APPROVED, dto.idTutor())).thenReturn(3L);

        assertDoesNotThrow(() -> tutorValidation.validate(dto));
    }

    @Test
    @DisplayName("Should not validate when a tutor reached adoption limit")
    void shoulNotdValidateTutorReachedAdoptionLimit() {
        var dto = new RequestedAdoptionDTO(UUID.randomUUID(), UUID.randomUUID(), "any");
        when(repository.countByStatusAndTutorId(AdoptionStatus.APPROVED, dto.idTutor())).thenReturn(5L);

        assertThrows(ValidationException.class, () -> tutorValidation.validate(dto));
    }
}