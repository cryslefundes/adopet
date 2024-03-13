package br.com.cryslefundes.adopet.api.service;

import br.com.cryslefundes.adopet.api.core.dto.adoption.ApprovedAdoptionDTO;
import br.com.cryslefundes.adopet.api.core.dto.adoption.DeniedAdoptionDTO;
import br.com.cryslefundes.adopet.api.core.dto.adoption.RequestedAdoptionDTO;
import br.com.cryslefundes.adopet.api.core.entity.Adoption;
import br.com.cryslefundes.adopet.api.core.entity.Pet;
import br.com.cryslefundes.adopet.api.core.entity.Tutor;
import br.com.cryslefundes.adopet.api.core.enums.AdoptionStatus;
import br.com.cryslefundes.adopet.api.repository.AdoptionRepository;
import br.com.cryslefundes.adopet.api.repository.PetRepository;
import br.com.cryslefundes.adopet.api.repository.TutorRepository;
import br.com.cryslefundes.adopet.api.service.validation.IValidation;
import br.com.cryslefundes.adopet.api.service.validation.PetIsAvailableValidation;
import br.com.cryslefundes.adopet.api.service.validation.TutorWithAdoptionLimitValidation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class AdoptionServiceTest {

    @InjectMocks
    private AdoptionService service;
    @Mock
    private AdoptionRepository repository;
    @Mock
    private PetRepository petRepository;
    @Mock
    private TutorRepository tutorRepository;
    @Mock
    private Pet pet;
    @Mock
    private Tutor tutor;
    @Captor
    private ArgumentCaptor<Adoption> adoptionCaptor;
    @Spy
    private List<IValidation<RequestedAdoptionDTO>> validations = new ArrayList<>();
    @Mock
    private PetIsAvailableValidation validation1;
    @Mock
    private TutorWithAdoptionLimitValidation validation2;
    @Spy
    Adoption adoption;

    @Test
    @DisplayName("Should save an adoption on the database when someone request an adoption")
    void shouldSaveAdoption() {
        var dto = new RequestedAdoptionDTO(UUID.randomUUID(), UUID.randomUUID(), "any");
        given(petRepository.getReferenceById(dto.idPet())).willReturn(pet);
        given(tutorRepository.getReferenceById(dto.idTutor())).willReturn(tutor);

        service.requestAdoption(dto);

        then(repository).should().save(adoptionCaptor.capture());
        Adoption adoptionSaved = adoptionCaptor.getValue();

        assertThat(adoptionSaved.getPet()).isEqualTo(pet);
        assertThat(adoptionSaved.getTutor()).isEqualTo(tutor);
        assertThat(adoptionSaved.getPurpose()).isEqualTo(dto.purpose());
    }

    @Test
    @DisplayName("Should pass through in a validation list when someone request an adoption")
    void shouldValidateRequestedAdoptionDTO() {
        var dto = new RequestedAdoptionDTO(UUID.randomUUID(), UUID.randomUUID(), "any");
        given(petRepository.getReferenceById(dto.idPet())).willReturn(pet);
        given(tutorRepository.getReferenceById(dto.idTutor())).willReturn(tutor);
        validations.add(validation1);
        validations.add(validation2);

        service.requestAdoption(dto);

        then(validation1).should().validate(dto);
        then(validation2).should().validate(dto);
    }

    @Test
    @DisplayName("Should approve an adoption when some admin request")
    void shouldApproveAnAdoption() {
        var dto = new ApprovedAdoptionDTO(UUID.randomUUID());
        given(repository.getReferenceById(dto.id())).willReturn(adoption);
        given(adoption.getPet()).willReturn(pet);
        given(pet.getAdopted()).willReturn(true);

        service.approveAdoption(dto);

        then(adoption).should().markAsApproved();
        then(adoption.getPet()).should().markAsAdopted();

        assertThat(adoption.getStatus()).isEqualTo(AdoptionStatus.APPROVED);
        assertThat(adoption.getPet().getAdopted()).isEqualTo(true);
    }

    @Test
    @DisplayName("Should denied an adoption when some admin request")
    void shouldDeniedAnAdoption() {
        var dto = new DeniedAdoptionDTO(UUID.randomUUID(), "any");
        given(repository.getReferenceById(dto.id())).willReturn(adoption);

        service.disapproveAdoption(dto);

        then(adoption).should().markAsDenied(dto.justification());
        assertThat(adoption.getStatus()).isEqualTo(AdoptionStatus.DENIED);
    }

}