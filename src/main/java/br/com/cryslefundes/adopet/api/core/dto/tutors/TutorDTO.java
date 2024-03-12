package br.com.cryslefundes.adopet.api.core.dto.tutors;

import br.com.cryslefundes.adopet.api.core.entity.Address;
import br.com.cryslefundes.adopet.api.core.entity.Tutor;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

public record TutorDTO (
        @NotNull
        UUID id,
        @NotBlank
        String name,
        @NotBlank
        @Pattern(regexp = "\\(?\\d{2}\\)?\\d?\\d{4}-?\\d{4}")
        String phone,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Pattern(regexp = "\\\\d{3}\\\\.\\\\d{3}\\\\.\\\\d{3}\\\\-\\\\d{2}")
        String document,
        @NotNull
        @Valid
        Address address,
        @NotNull
        URI location
) {
        public TutorDTO(Tutor tutor) {
                this(
                        tutor.getId(),
                        tutor.getName(),
                        tutor.getPhone(),
                        tutor.getEmail(),
                        tutor.getDocument(),
                        tutor.getAddress(),
                        UriComponentsBuilder.newInstance()
                                .path("/tutors/{id}")
                                .buildAndExpand(tutor.getId())
                                .toUri()
                );
        }
}
