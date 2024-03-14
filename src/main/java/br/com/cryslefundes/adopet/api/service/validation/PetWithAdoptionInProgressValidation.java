package br.com.cryslefundes.adopet.api.service.validation;

import br.com.cryslefundes.adopet.api.core.dto.adoption.RequestedAdoptionDTO;
import br.com.cryslefundes.adopet.api.core.enums.AdoptionStatus;
import br.com.cryslefundes.adopet.api.repository.AdoptionRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PetWithAdoptionInProgressValidation implements IValidation<RequestedAdoptionDTO> {
    @Autowired
    private AdoptionRepository adoptionRepository;

    @Override
    public void validate(RequestedAdoptionDTO dto) {
        Boolean isPetWithAdoptionInProgress = adoptionRepository.existsByPetIdAndStatus(dto.idPet(), AdoptionStatus.WAITING_EVALUATION);

        if (isPetWithAdoptionInProgress) {
            throw new ValidationException("This pet already has an adoption in progress.");
        }
    }
}
