package br.com.cryslefundes.adopet.api.core.dto.pet;

import br.com.cryslefundes.adopet.api.core.enums.PetType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterPetDTO (
        @NotBlank
        String name,
        @NotBlank
        String breed,
        Integer age,
        Float weight,
        @NotNull
        PetType type
) {
}
