package br.com.cryslefundes.adopet.api.service;

import br.com.cryslefundes.adopet.api.core.dto.EmailDTO;
import br.com.cryslefundes.adopet.api.core.dto.tutors.RegisterTutorDTO;
import br.com.cryslefundes.adopet.api.core.dto.tutors.TutorDTO;
import br.com.cryslefundes.adopet.api.core.dto.tutors.UpdateTutorDTO;
import br.com.cryslefundes.adopet.api.core.entity.Tutor;
import br.com.cryslefundes.adopet.api.core.useCase.TutorUseCase;
import br.com.cryslefundes.adopet.api.producer.EmailProducer;
import br.com.cryslefundes.adopet.api.repository.TutorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TutorService implements TutorUseCase {
    @Autowired
    private TutorRepository repository;
    @Autowired
    private EmailProducer producer;

    @Override
    public Page<TutorDTO> showAllTutors(Pageable pagination) {
        return repository.findAll(pagination).map(TutorDTO::new);
    }

    @Override
    public TutorDTO showTutor(UUID id) {
        Tutor tutor = repository.getReferenceById(id);
        return new TutorDTO(tutor);
    }

    @Override
    public TutorDTO registerTutor(RegisterTutorDTO dto) {
        Tutor tutor = new Tutor(dto);
        repository.save(tutor);

        var emailDTO = new EmailDTO(
                tutor.getName(),
                tutor.getEmail(),
                "Tutor registered with success",
                "Welcome to our platform!! Take a good look in pets available around you right now."
        );
        producer.publishMessageEmail(emailDTO);

        return new TutorDTO(tutor);
    }

    @Override
    public TutorDTO updateTutor(UpdateTutorDTO dto) {
        Optional<Tutor> mayberTutor = repository.findById(dto.id());
        if (mayberTutor.isPresent()) {
            Tutor tutor = mayberTutor.get();
            tutor.updateInfo(dto);
            return new TutorDTO(tutor);
        }
        throw new EntityNotFoundException();
    }
}
