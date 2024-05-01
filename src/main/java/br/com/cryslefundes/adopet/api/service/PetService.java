package br.com.cryslefundes.adopet.api.service;

import br.com.cryslefundes.adopet.api.core.dto.pet.PetDTO;
import br.com.cryslefundes.adopet.api.core.dto.pet.RegisterPetDTO;
import br.com.cryslefundes.adopet.api.core.dto.pet.UpdatePetDTO;
import br.com.cryslefundes.adopet.api.core.entity.Pet;
import br.com.cryslefundes.adopet.api.core.entity.Shelter;
import br.com.cryslefundes.adopet.api.core.useCase.PetUseCase;
import br.com.cryslefundes.adopet.api.repository.PetRepository;
import br.com.cryslefundes.adopet.api.repository.ShelterRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PetService implements PetUseCase {
    @Autowired
    private PetRepository repository;
    @Autowired
    private ShelterRepository shelterRepository;

    @Override
    public Page<PetDTO> showAllPetsAvailable(Pageable pagination) {
       return repository.findAllByAdoptedFalse(pagination).map(PetDTO::new);
    }

    @Override
    public Page<PetDTO> showAllPets(Pageable pagination) {
        return repository.findAll(pagination).map(PetDTO::new);
    }

    @Override
    public PetDTO showPet(UUID id) {
        Pet pet = repository.getReferenceById(id);
        return new PetDTO(pet);
    }

    @Override
    public PetDTO registerPet(RegisterPetDTO dto) {
        Shelter shelter = shelterRepository.getReferenceById(dto.shelterId());
        Pet pet = new Pet(
                dto.name(),
                dto.breed(),
                dto.age(),
                dto.weight(),
                dto.type(),
                shelter
        );
        repository.save(pet);
        return new PetDTO(pet);
    }

    @Override
    public PetDTO updatePet(UpdatePetDTO dto) {
        Optional<Pet> maybePet = repository.findById(dto.id());
        if (maybePet.isPresent()) {
            Pet pet = maybePet.get();
            pet.updateInfo(dto);
            return new PetDTO(pet);
        }
        throw new EntityNotFoundException();
    }
}
