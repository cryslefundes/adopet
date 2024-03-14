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
class PetWithAdoptionInProgressValidationTest {
    @InjectMocks
    private PetWithAdoptionInProgressValidation petValidation;
    @Mock
    private AdoptionRepository repository;

    @Test
    @DisplayName("Should validate pet with adoption not in progress")
    void shouldValidatePetAdoptionNotInProgress() {
        var dto = new RequestedAdoptionDTO(UUID.randomUUID(), UUID.randomUUID(), "any");
        when(repository.existsByPetIdAndStatus(dto.idPet(), AdoptionStatus.WAITING_EVALUATION)).thenReturn(false);

        assertDoesNotThrow(() -> petValidation.validate(dto));
    }

    @Test
    @DisplayName("Should not validate pet with adoption in progress")
    void shouldNotValidatePetAdoptionInProgress() {
        var dto = new RequestedAdoptionDTO(UUID.randomUUID(), UUID.randomUUID(), "any");
        when(repository.existsByPetIdAndStatus(dto.idPet(), AdoptionStatus.WAITING_EVALUATION)).thenReturn(true);

        assertThrows(ValidationException.class, () -> petValidation.validate(dto));
    }
}