package br.com.cryslefundes.adopet.api.service;

import br.com.cryslefundes.adopet.api.core.dto.EmailDTO;
import br.com.cryslefundes.adopet.api.core.dto.shelter.RegisterShelterDTO;
import br.com.cryslefundes.adopet.api.core.dto.shelter.ShelterDTO;
import br.com.cryslefundes.adopet.api.core.dto.shelter.UpdateShelterDTO;
import br.com.cryslefundes.adopet.api.core.entity.Shelter;
import br.com.cryslefundes.adopet.api.core.useCase.ShelterUseCase;
import br.com.cryslefundes.adopet.api.producer.EmailProducer;
import br.com.cryslefundes.adopet.api.repository.ShelterRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ShelterService implements ShelterUseCase {
    @Autowired
    private ShelterRepository repository;
    @Autowired
    private EmailProducer producer;

    @Override
    public Page<ShelterDTO> showAllShelters(Pageable pagination) {
        return repository.findAll(pagination).map(ShelterDTO::new);
    }

    @Override
    public ShelterDTO showShelter(UUID id) {
        Shelter shelter = repository.getReferenceById(id);
        return new ShelterDTO(shelter);
    }

    @Override
    public ShelterDTO registerShelter(RegisterShelterDTO dto) {
        Shelter shelter = new Shelter(dto);
        repository.save(shelter);

        var emailDTO = new EmailDTO(
                shelter.getName(),
                shelter.getEmail(),
                "Shelter registered with success",
                "Welcome to our platform!! Start now registering your pets available to adoption."
        );
        producer.publishMessageEmail(emailDTO);

        return new ShelterDTO(shelter);
    }

    @Override
    public ShelterDTO updateShelter(UpdateShelterDTO dto) {
        Optional<Shelter> maybeShelter = repository.findById(dto.id());
        if (maybeShelter.isPresent()) {
            Shelter shelter = maybeShelter.get();
            shelter.updateInfo(dto);
            return new ShelterDTO(shelter);
        }
        throw new EntityNotFoundException();
    }
}
