package br.com.cryslefundes.adopet.api.core.useCase;

import br.com.cryslefundes.adopet.api.core.dto.shelter.RegisterShelterDTO;
import br.com.cryslefundes.adopet.api.core.dto.shelter.ShelterDTO;
import br.com.cryslefundes.adopet.api.core.dto.shelter.UpdateShelterDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface ShelterUseCase {
    Page<ShelterDTO> showAllShelters(Pageable pagination);
    ShelterDTO showShelter(UUID id);
    ShelterDTO registerShelter(RegisterShelterDTO dto);
    ShelterDTO updateShelter(UpdateShelterDTO dto);
}
