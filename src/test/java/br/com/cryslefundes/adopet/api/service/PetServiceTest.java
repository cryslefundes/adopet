package br.com.cryslefundes.adopet.api.service;

import br.com.cryslefundes.adopet.api.core.dto.pet.PetDTO;
import br.com.cryslefundes.adopet.api.core.dto.pet.UpdatePetDTO;
import br.com.cryslefundes.adopet.api.core.entity.Adoption;
import br.com.cryslefundes.adopet.api.core.entity.Pet;
import br.com.cryslefundes.adopet.api.core.entity.Shelter;
import br.com.cryslefundes.adopet.api.core.enums.PetType;
import br.com.cryslefundes.adopet.api.repository.PetRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class PetServiceTest {
    @InjectMocks
    private PetService service;
    @Mock
    private PetRepository repository;
    @Mock
    private UpdatePetDTO dto;

    private Pet pet;


    @BeforeEach
    void setUp() {
        this.pet = new Pet(
                UUID.randomUUID(),
                "Belinha",
                "chihuahua",
                5,
                false,
                5.3f,
                PetType.DOG,
                new Adoption(),
                new Shelter()
        );
    }

    @Test
    @DisplayName("Should return updated info when someone request an update of a pet")
    void shouldReturnUpdatedInfo() {
        given(dto.name()).willReturn("Thor");
        given(repository.findById(dto.id())).willReturn(Optional.of(pet));

        service.updatePet(dto);

        assertThat(pet.getName()).isEqualTo("Thor");
    }

    @Test
    @DisplayName("Should not return updated info when someone request an update of a pet send only his id")
    void shouldNotReturnUpdatedInfo() {
        given(repository.findById(dto.id())).willReturn(Optional.of(pet));

        var petDTO = service.updatePet(dto);

        assertThat(petDTO).isEqualTo(new PetDTO(pet));
    }

    @Test
    @DisplayName("Should throws EntityNotFoundException when someone request a invalid pet id")
    void shouldThrowsEntityNotFoundException() {
        var updateDTO = new UpdatePetDTO(UUID.randomUUID(), null, null, null, null, null);
        assertThrows(EntityNotFoundException.class, () -> service.updatePet(updateDTO));
    }
}