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
class TutorWithAdoptionInProgressValidationTest {
    @InjectMocks
    private TutorWithAdoptionInProgressValidation tutorValidation;
    @Mock
    private AdoptionRepository repository;

    @Test
    @DisplayName("Should validate when a tutor doesnt have an adoption in progress")
    void shouldValidateTutorWithoutAdoptionInProgress() {
        var dto = new RequestedAdoptionDTO(UUID.randomUUID(), UUID.randomUUID(), "any");
        when(repository.existsByTutorIdAndStatus(dto.idTutor(), AdoptionStatus.WAITING_EVALUATION)).thenReturn(false);

        assertDoesNotThrow(() -> tutorValidation.validate(dto));
    }

    @Test
    @DisplayName("Should not validate when a tutor has an adoption in progress")
    void shouldNotValidateTutorWithAdoptionInProgress() {
        var dto = new RequestedAdoptionDTO(UUID.randomUUID(), UUID.randomUUID(), "any");
        when(repository.existsByTutorIdAndStatus(dto.idTutor(), AdoptionStatus.WAITING_EVALUATION)).thenReturn(true);

        assertThrows(ValidationException.class, () -> tutorValidation.validate(dto));
    }
}
