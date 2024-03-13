package br.com.cryslefundes.adopet.api.core.dto.adoption;

import br.com.cryslefundes.adopet.api.core.entity.Adoption;
import br.com.cryslefundes.adopet.api.core.enums.AdoptionStatus;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.Instant;
import java.util.UUID;

public record AdoptionDTO (
        @NotNull
        UUID id,
        @NotNull
        @JsonAlias("date_of")
        Instant dateOf,
        @NotBlank
        String purpose,
        @NotNull
        AdoptionStatus status,
        @NotNull
        @JsonAlias("tutor_id")
        UUID tutorId,
        @NotNull
        @JsonAlias("pet_id")
        UUID petId,
        @NotNull
        URI location
) {
    public AdoptionDTO(Adoption adoption) {
        this(
                adoption.getId(),
                adoption.getDateOf(),
                adoption.getPurpose(),
                adoption.getStatus(),
                adoption.getTutor().getId(),
                adoption.getPet().getId(),
                UriComponentsBuilder.newInstance()
                        .path("adoptions/{id}")
                        .buildAndExpand(adoption.getId())
                        .toUri()
        );
    }
}
