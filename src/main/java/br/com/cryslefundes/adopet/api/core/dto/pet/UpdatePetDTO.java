package br.com.cryslefundes.adopet.api.core.dto.pet;

import br.com.cryslefundes.adopet.api.core.enums.PetType;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UpdatePetDTO(
        @NotNull
        UUID id,
        String name,
        String breed,
        Integer age,
        Float weight,
        @JsonAlias("pet_type")
        PetType petType
) {
}
