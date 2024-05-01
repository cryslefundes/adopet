package br.com.cryslefundes.adopet.api.service;

import br.com.cryslefundes.adopet.api.core.dto.EmailDTO;
import br.com.cryslefundes.adopet.api.core.dto.adoption.AdoptionDTO;
import br.com.cryslefundes.adopet.api.core.dto.adoption.ApprovedAdoptionDTO;
import br.com.cryslefundes.adopet.api.core.dto.adoption.DeniedAdoptionDTO;
import br.com.cryslefundes.adopet.api.core.dto.adoption.RequestedAdoptionDTO;
import br.com.cryslefundes.adopet.api.core.entity.Adoption;
import br.com.cryslefundes.adopet.api.core.entity.Pet;
import br.com.cryslefundes.adopet.api.core.entity.Tutor;
import br.com.cryslefundes.adopet.api.core.useCase.AdoptionUseCase;
import br.com.cryslefundes.adopet.api.producer.EmailProducer;
import br.com.cryslefundes.adopet.api.repository.AdoptionRepository;
import br.com.cryslefundes.adopet.api.repository.PetRepository;
import br.com.cryslefundes.adopet.api.repository.TutorRepository;
import br.com.cryslefundes.adopet.api.service.validation.IValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdoptionService implements AdoptionUseCase {
    @Autowired
    private AdoptionRepository repository;
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private TutorRepository tutorRepository;
    @Autowired
    private List<IValidation<RequestedAdoptionDTO>> validations;

    @Autowired
    private EmailProducer producer;

    @Override
    public void disapproveAdoption(DeniedAdoptionDTO dto) {
        Adoption adoption = repository.getReferenceById(dto.id());
        adoption.markAsDenied(dto.justification());

        Tutor tutor = adoption.getTutor();
        var emailDTO = new EmailDTO(
                tutor.getName(),
                tutor.getEmail(),
                "Adoption denied",
                "Your adoption request was denied because: " + dto.justification()
        );
        producer.publishMessageEmail(emailDTO);
    }

    @Override
    public void approveAdoption(ApprovedAdoptionDTO dto) {
        Adoption adoption = repository.getReferenceById(dto.id());
        adoption.markAsApproved();
        adoption.getPet().markAsAdopted();

        Tutor tutor = adoption.getTutor();
        var emailDTO = new EmailDTO(
                tutor.getName(),
                tutor.getEmail(),
                "Adoption accepted",
                "Congratulations!! Your adoption was approved!"
        );
        producer.publishMessageEmail(emailDTO);
    }

    @Override
    public AdoptionDTO requestAdoption(RequestedAdoptionDTO dto) {
        Pet pet = petRepository.getReferenceById(dto.petId());
        Tutor tutor = tutorRepository.getReferenceById(dto.tutorId());

        validations.forEach(v -> v.validate(dto));

        Adoption adoption = new Adoption(pet, tutor, dto.purpose());
        repository.save(adoption);

        var emailDTO = new EmailDTO(
                tutor.getName(),
                tutor.getEmail(),
                "Adoption requested with success",
                "Your adoption was requested with success! Please, be patient, we are analyzing your pet adoption form."
        );
        producer.publishMessageEmail(emailDTO);

        return new AdoptionDTO(adoption);
    }
}
