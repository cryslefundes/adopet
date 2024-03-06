package br.com.cryslefundes.adopet.api.service;

import br.com.cryslefundes.adopet.api.core.dto.AddressDTO;
import br.com.cryslefundes.adopet.api.core.dto.shelter.ShelterDTO;
import br.com.cryslefundes.adopet.api.core.dto.shelter.UpdateShelterDTO;
import br.com.cryslefundes.adopet.api.core.entity.Address;
import br.com.cryslefundes.adopet.api.core.entity.Shelter;
import br.com.cryslefundes.adopet.api.repository.ShelterRepository;
import jakarta.persistence.EntityNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ShelterServiceTest {
    @InjectMocks
    private ShelterService service;
    @Mock
    private ShelterRepository repository;
    @Mock
    private UpdateShelterDTO dto;
    @Mock
    private AddressDTO addressDTO;

    private Shelter shelter;

    @BeforeEach
    void setUp() {
        this.shelter = new Shelter(
                UUID.randomUUID(),
                "Abrigo Felicidade Animal",
                "(21)97503-4352",
                "abrigo_felicidade@gmail.com",
                new Address(new AddressDTO(
                        "rua random",
                        30,
                        null,
                        "Rio de janeiro",
                        "Rio de janeiro",
                        "21212321"
                )),
                new ArrayList<>()
        );
    }

    @Test
    @DisplayName("Should return updated info when someone update info about shelter")
    void shouldReturnUpdatedInfoCaseOne() {

        given(dto.email()).willReturn("abrigo.felicidade.animal@admin.com");

        given(repository.findById(dto.id())).willReturn(Optional.of(shelter));

        service.updateShelter(dto);

        assertThat(shelter.getEmail()).isEqualTo("abrigo.felicidade.animal@admin.com");
    }

    @Test
    @DisplayName("Should return updated info when someone update info about shelter address")
    void shouldReturnUpdatedInfoCaseTwo() {

        given(dto.address()).willReturn(addressDTO);
        given(dto.address().city()).willReturn("São Paulo");

        given(repository.findById(dto.id())).willReturn(Optional.of(shelter));

        service.updateShelter(dto);

        assertThat(shelter.getAddress().getCity()).isEqualTo("São Paulo");
    }

    @Test
    @DisplayName("Should return shelter not uptaded when someone send only the id shelter")
    void shouldReturnShelterNotUpdated() {

        given(repository.findById(dto.id())).willReturn(Optional.of(shelter));

        ShelterDTO shelterDTO = service.updateShelter(dto);

        assertThat(new ShelterDTO(shelter)).isEqualTo(shelterDTO);
    }

    @Test
    @DisplayName("Should throws EntityNotFoundException when someone send an id shelter invalid")
    void shouldThrowsEntityNotFound() {

        UpdateShelterDTO arrangeDTO = new UpdateShelterDTO(UUID.randomUUID(), null, null, null, null);

        assertThrows(EntityNotFoundException.class, () -> service.updateShelter(arrangeDTO));
    }

}