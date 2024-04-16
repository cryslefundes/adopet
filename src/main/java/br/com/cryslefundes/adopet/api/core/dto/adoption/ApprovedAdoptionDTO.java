package br.com.cryslefundes.adopet.api.core.dto.adoption;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ApprovedAdoptionDTO (
        @NotNull UUID id
){
}
