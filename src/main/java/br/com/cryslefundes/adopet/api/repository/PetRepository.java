package br.com.cryslefundes.adopet.api.repository;

import br.com.cryslefundes.adopet.api.core.dto.pet.PetDTO;
import br.com.cryslefundes.adopet.api.core.entity.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PetRepository extends JpaRepository<Pet, UUID> {

    Boolean existsByIdAndAdopted(UUID petId, boolean adopted);

    Page<PetDTO> findAllByAdoptedFalse(Pageable pagination);
}
