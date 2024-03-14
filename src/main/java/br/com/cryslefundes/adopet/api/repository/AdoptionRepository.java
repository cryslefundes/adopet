package br.com.cryslefundes.adopet.api.repository;

import br.com.cryslefundes.adopet.api.core.entity.Adoption;
import br.com.cryslefundes.adopet.api.core.enums.AdoptionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface AdoptionRepository extends JpaRepository<Adoption, UUID> {
    Boolean existsByPetIdAndStatus(UUID petId, AdoptionStatus adoptionStatus);

    Boolean existsByTutorIdAndStatus(UUID tutorId, AdoptionStatus adoptionStatus);

    long countByStatusAndTutorId(AdoptionStatus adoptionStatus, UUID tutorId);
}
