package br.com.cryslefundes.adopet.api.controller;

import br.com.cryslefundes.adopet.api.core.dto.shelter.RegisterShelterDTO;
import br.com.cryslefundes.adopet.api.core.dto.shelter.ShelterDTO;
import br.com.cryslefundes.adopet.api.core.dto.shelter.UpdateShelterDTO;
import br.com.cryslefundes.adopet.api.service.ShelterService;
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
@RequestMapping("/shelters")
public class ShelterController {
    @Autowired
    private ShelterService service;

    @GetMapping
    public ResponseEntity<Page<ShelterDTO>> showAllShelters(@ParameterObject @PageableDefault(sort = "name") Pageable pagination) {
        return ResponseEntity.ok(service.showAllShelters(pagination));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShelterDTO> showShelter(@PathVariable UUID id) {
        return ResponseEntity.ok(service.showShelter(id));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ShelterDTO> registerShelter(@RequestBody @Valid RegisterShelterDTO dto) {
        ShelterDTO shelterDTO = service.registerShelter(dto);
        return ResponseEntity.created(shelterDTO.location()).body(shelterDTO);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<ShelterDTO> updateShelter(@RequestBody @Valid UpdateShelterDTO dto) {
        return ResponseEntity.ok(service.updateShelter(dto));
    }
}
