package br.com.cryslefundes.adopet.api.core.dto.adoption;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record DeniedAdoptionDTO(
        @NotNull UUID id,
        @NotBlank String justification
) {
}
