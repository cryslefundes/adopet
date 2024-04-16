package br.com.cryslefundes.adopet.api.core.dto.adoption;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record RequestedAdoptionDTO(
        @NotNull
        @JsonAlias("tutor_id")
        UUID tutorId,
        @NotNull
        @JsonAlias("pet_id")
        UUID petId,
        @NotBlank String purpose
) {
}
