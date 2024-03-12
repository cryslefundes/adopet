package br.com.cryslefundes.adopet.api.service;

import br.com.cryslefundes.adopet.api.core.dto.AddressDTO;
import br.com.cryslefundes.adopet.api.core.dto.tutors.TutorDTO;
import br.com.cryslefundes.adopet.api.core.dto.tutors.UpdateTutorDTO;
import br.com.cryslefundes.adopet.api.core.entity.Address;
import br.com.cryslefundes.adopet.api.core.entity.Tutor;
import br.com.cryslefundes.adopet.api.repository.TutorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class TutorServiceTest {
    @InjectMocks
    private TutorService service;
    @Mock
    private TutorRepository repository;
    @Mock
    private UpdateTutorDTO dto;
    @Mock
    private AddressDTO addressDTO;
    private Tutor tutor;


    @BeforeEach
    void setUp() {
        this.tutor = new Tutor(
                UUID.randomUUID(),
                "Crystian",
                "21984125307",
                "crystian.tutor@dev.com",
                "143.123.111-99",
                new Address(new AddressDTO(
                        "rua random",
                        30,
                        null,
                        "Rio de janeiro",
                        "Rio de janeiro",
                        "21212321"
                )),
                true,
                new ArrayList<>()
        );
    }

    @Test
    @DisplayName("Should return updated info when someone request a update of a tutor")
    void shouldReturnUpdatedInfoCaseOne() {
        given(dto.phone()).willReturn("(21)98888-9911");
        given(repository.findById(dto.id())).willReturn(Optional.of(tutor));

        service.updateTutor(dto);

        assertThat(tutor.getPhone()).isEqualTo("(21)98888-9911");
    }

    @Test
    @DisplayName("Should return updated info when someone request a update of a tutor address")
    void shouldReturnUpdatedInfoCaseTwo() {
        given(dto.address()).willReturn(addressDTO);
        given(dto.address().zipCode()).willReturn("21312987");
        given(repository.findById(dto.id())).willReturn(Optional.of(tutor));

        service.updateTutor(dto);

        assertThat(tutor.getAddress().getZipCode()).isEqualTo("21312987");
    }

    @Test
    @DisplayName("Should not return updated info when someone request a update of a tutor send just his id")
    void shouldReturnNotUpdatedInfo() {
        given(repository.findById(dto.id())).willReturn(Optional.of(tutor));

        var tutorDTO = service.updateTutor(dto);

        assertThat(new TutorDTO(tutor)).isEqualTo(tutorDTO);
    }

    @Test
    @DisplayName("Should throws EntityNotFoundException when someone request a invalid tutor id")
    void shouldThrowsEntityNotFoundException() {
        var updateTutorDTO = new UpdateTutorDTO(UUID.randomUUID(), null, null, null, null, null);
        assertThrows(EntityNotFoundException.class, () -> service.updateTutor(updateTutorDTO));
    }
}

