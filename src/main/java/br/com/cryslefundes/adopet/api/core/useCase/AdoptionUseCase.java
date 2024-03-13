package br.com.cryslefundes.adopet.api.core.useCase;

import br.com.cryslefundes.adopet.api.core.dto.adoption.AdoptionDTO;
import br.com.cryslefundes.adopet.api.core.dto.adoption.ApprovedAdoptionDTO;
import br.com.cryslefundes.adopet.api.core.dto.adoption.DeniedAdoptionDTO;
import br.com.cryslefundes.adopet.api.core.dto.adoption.RequestedAdoptionDTO;

public interface AdoptionUseCase {

    void disapproveAdoption(DeniedAdoptionDTO dto);
    void approveAdoption(ApprovedAdoptionDTO dto);
    AdoptionDTO requestAdoption(RequestedAdoptionDTO dto);

}
