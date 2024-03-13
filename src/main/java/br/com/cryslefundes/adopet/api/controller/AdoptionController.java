package br.com.cryslefundes.adopet.api.controller;

import br.com.cryslefundes.adopet.api.core.dto.adoption.AdoptionDTO;
import br.com.cryslefundes.adopet.api.core.dto.adoption.ApprovedAdoptionDTO;
import br.com.cryslefundes.adopet.api.core.dto.adoption.DeniedAdoptionDTO;
import br.com.cryslefundes.adopet.api.core.dto.adoption.RequestedAdoptionDTO;
import br.com.cryslefundes.adopet.api.service.AdoptionService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adoptions")
public class AdoptionController {
    @Autowired
    private AdoptionService service;

    @PostMapping
    @Transactional
    public ResponseEntity<AdoptionDTO> requestAdoption(@RequestBody @Valid RequestedAdoptionDTO dto) {
        AdoptionDTO adoptionDTO = service.requestAdoption(dto);
        return ResponseEntity.created(adoptionDTO.location()).body(adoptionDTO);
    }

    @PutMapping("/approve")
    @Transactional
    public ResponseEntity<String> approved(@RequestBody @Valid ApprovedAdoptionDTO dto) {
        service.approveAdoption(dto);
        return ResponseEntity.ok("Adoption approved!");
    }

    @PutMapping("/disapprove")
    @Transactional
    public ResponseEntity<String> disapproved(@RequestBody @Valid DeniedAdoptionDTO dto) {
       service.disapproveAdoption(dto);
       return ResponseEntity.ok("Adoption denied!");
    }
}
