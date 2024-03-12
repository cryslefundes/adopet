package br.com.cryslefundes.adopet.api.core.useCase;

import br.com.cryslefundes.adopet.api.core.dto.tutors.RegisterTutorDTO;
import br.com.cryslefundes.adopet.api.core.dto.tutors.TutorDTO;
import br.com.cryslefundes.adopet.api.core.dto.tutors.UpdateTutorDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface TutorUseCase {
    Page<TutorDTO> showAllTutors(Pageable pagination);
    TutorDTO showTutor(UUID id);
    TutorDTO registerTutor(RegisterTutorDTO dto);
    TutorDTO updateTutor(UpdateTutorDTO dto);
}
