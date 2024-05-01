package br.com.cryslefundes.adopet.api.service.validation;

import br.com.cryslefundes.adopet.api.core.dto.adoption.RequestedAdoptionDTO;
import br.com.cryslefundes.adopet.api.core.enums.AdoptionStatus;
import br.com.cryslefundes.adopet.api.repository.AdoptionRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TutorWithAdoptionLimitValidation implements IValidation<RequestedAdoptionDTO> {
    @Autowired
    private AdoptionRepository adoptionRepository;

    @Override
    public void validate(RequestedAdoptionDTO dto) {
        long adoptionCount = adoptionRepository.countByStatusAndTutorId(AdoptionStatus.APPROVED, dto.tutorId());

        if (adoptionCount >= 5L) {
            throw new ValidationException("Tutor has reached the adoption limit.");
        }
    }
}
