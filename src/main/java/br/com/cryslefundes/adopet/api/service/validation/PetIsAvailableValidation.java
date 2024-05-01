package br.com.cryslefundes.adopet.api.service.validation;

import br.com.cryslefundes.adopet.api.core.dto.adoption.RequestedAdoptionDTO;
import br.com.cryslefundes.adopet.api.repository.PetRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PetIsAvailableValidation implements IValidation<RequestedAdoptionDTO> {
    @Autowired
    private PetRepository petRepository;

    @Override
    public void validate(RequestedAdoptionDTO dto) {
        Boolean isPetAvailable = petRepository.existsByIdAndAdopted(dto.petId(), false);
        if (Boolean.FALSE.equals(isPetAvailable)) {
            throw new ValidationException("This pet is already adopted.");
        }
    }
}
