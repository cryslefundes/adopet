package br.com.cryslefundes.adopet.api.core.dto.pet;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.UUID;

public record RegisterPetDTO (
        @NotBlank
        String name,
        @NotBlank
        String breed,
        @PositiveOrZero
        @Max(value = 20)
        Integer age,
        @PositiveOrZero
        Float weight,
        @NotNull
        @JsonAlias("shelter_id")
        UUID shelterId,
        @NotNull
        String type
) {
}
