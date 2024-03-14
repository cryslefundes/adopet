package br.com.cryslefundes.adopet.api.service.validation;

import br.com.cryslefundes.adopet.api.core.dto.adoption.RequestedAdoptionDTO;
import br.com.cryslefundes.adopet.api.repository.PetRepository;
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
class PetIsAvailableValidationTest {
    @InjectMocks
    private PetIsAvailableValidation petValidation;
    @Mock
    private PetRepository repository;

    @Test
    @DisplayName("Should validate a pet that is available")
    void shouldValidatePetAvailable() {
        var dto = new RequestedAdoptionDTO(UUID.randomUUID(), UUID.randomUUID(), "any");
        when(repository.existsByIdAndAdopted(dto.idPet(), false)).thenReturn(true);

        assertDoesNotThrow(() -> petValidation.validate(dto));
    }

    @Test
    @DisplayName("Should not validate a pet that is not available")
    void shouldNotValidatePetNotAvailable() {
        var dto = new RequestedAdoptionDTO(UUID.randomUUID(), UUID.randomUUID(), "any");
        when(repository.existsByIdAndAdopted(dto.idPet(), false)).thenReturn(false);

        assertThrows(ValidationException.class, () -> petValidation.validate(dto));
    }

}