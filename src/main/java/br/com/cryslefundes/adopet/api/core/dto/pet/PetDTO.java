package br.com.cryslefundes.adopet.api.core.dto.pet;

import br.com.cryslefundes.adopet.api.core.entity.Pet;
import br.com.cryslefundes.adopet.api.core.enums.PetType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

public record PetDTO(
        @NotNull
        UUID id,
        @NotBlank
        String name,
        @NotBlank
        String breed,
        Integer age,
        @NotNull
        Boolean adopted,
        Float weight,
        @NotNull
        PetType petType,
        @NotNull
        UUID shelterId,
        @NotNull
        URI location
) {
    public PetDTO(Pet pet) {
        this(
                pet.getId(),
                pet.getName(),
                pet.getBreed(),
                pet.getAge(),
                pet.getAdopted(),
                pet.getWeight(),
                pet.getPetType(),
                pet.getShelter().getId(),
                UriComponentsBuilder.newInstance()
                        .path("/pets/{id}")
                        .buildAndExpand(pet.getId())
                        .toUri()
        );
    }
}
