package br.com.cryslefundes.adopet.api.core.dto.pet;

import br.com.cryslefundes.adopet.api.core.enums.PetType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UpdatePetDTO(
        @NotNull
        UUID id,
        String name,
        String breed,
        Integer age,
        Float weight,
        PetType type
) {
}
