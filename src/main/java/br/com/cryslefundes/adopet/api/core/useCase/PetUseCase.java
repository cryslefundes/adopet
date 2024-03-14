package br.com.cryslefundes.adopet.api.core.useCase;

import br.com.cryslefundes.adopet.api.core.dto.pet.PetDTO;
import br.com.cryslefundes.adopet.api.core.dto.pet.RegisterPetDTO;
import br.com.cryslefundes.adopet.api.core.dto.pet.UpdatePetDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface PetUseCase {
    Page<PetDTO> showAllPetsAvailable(Pageable pagination);
    Page<PetDTO> showAllPets(Pageable pagination);
    PetDTO showPet(UUID id);
    PetDTO registerPet(RegisterPetDTO dto);
    PetDTO updatePet(UpdatePetDTO dto);
}
