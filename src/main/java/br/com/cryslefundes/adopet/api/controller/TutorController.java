package br.com.cryslefundes.adopet.api.controller;

import br.com.cryslefundes.adopet.api.core.dto.tutors.RegisterTutorDTO;
import br.com.cryslefundes.adopet.api.core.dto.tutors.TutorDTO;
import br.com.cryslefundes.adopet.api.core.dto.tutors.UpdateTutorDTO;
import br.com.cryslefundes.adopet.api.service.TutorService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/tutors")
public class TutorController {
    @Autowired
    private TutorService service;

    @GetMapping
    public ResponseEntity<Page<TutorDTO>> showAllTutors(@ParameterObject @PageableDefault(sort = "name") Pageable pagination) {
        return ResponseEntity.ok(service.showAllTutors(pagination));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TutorDTO> showTutor(@PathVariable UUID id) {
        return ResponseEntity.ok(service.showTutor(id));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TutorDTO> registerTutor(@RequestBody @Valid RegisterTutorDTO dto) {
        TutorDTO tutorDTO = service.registerTutor(dto);
        return ResponseEntity.created(tutorDTO.location()).body(tutorDTO);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<TutorDTO> updateTutor(UpdateTutorDTO dto) {
        return ResponseEntity.ok(service.updateTutor(dto));
    }
}
