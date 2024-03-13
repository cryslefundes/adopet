package br.com.cryslefundes.adopet.api.core.dto.adoption;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.net.URI;
import java.util.UUID;

public record RequestedAdoptionDTO(
        @NotNull UUID idTutor,
        @NotNull UUID idPet,
        @NotBlank String purpose
) {
}
