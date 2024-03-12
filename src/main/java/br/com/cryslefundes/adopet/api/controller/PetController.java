package br.com.cryslefundes.adopet.api.controller;

import br.com.cryslefundes.adopet.api.core.dto.pet.PetDTO;
import br.com.cryslefundes.adopet.api.core.dto.pet.RegisterPetDTO;
import br.com.cryslefundes.adopet.api.core.dto.pet.UpdatePetDTO;
import br.com.cryslefundes.adopet.api.service.PetService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/pets")
public class PetController {
    @Autowired
    private PetService service;

    @GetMapping
    public ResponseEntity<Page<PetDTO>> showAllPetsAvailable(@PageableDefault(sort = "breed")Pageable pagination) {
        return ResponseEntity.ok(service.showAllPetsAvailable(pagination));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetDTO> showPet(@PathVariable UUID id) {
        return ResponseEntity.ok(service.showPet(id));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<PetDTO> registerPet(@RequestBody @Valid RegisterPetDTO dto) {
        PetDTO petDTO = service.registerPet(dto);
        return ResponseEntity.created(petDTO.location()).body(petDTO);
    }

    @PutMapping
    public ResponseEntity<PetDTO> updatePet(UpdatePetDTO dto) {
        return ResponseEntity.ok(service.updatePet(dto));
    }
}
