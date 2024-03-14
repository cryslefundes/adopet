package br.com.cryslefundes.adopet.api.core.dto.shelter;

import br.com.cryslefundes.adopet.api.core.entity.Address;
import br.com.cryslefundes.adopet.api.core.entity.Shelter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.EqualsAndHashCode;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

public record ShelterDTO(
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
        @NotNull
        @Valid
        Address address,
        @NotBlank
        URI location
) {
        public ShelterDTO(Shelter shelter) {
                this(
                        shelter.getId(),
                        shelter.getName(),
                        shelter.getPhone(),
                        shelter.getEmail(),
                        shelter.getAddress(),
                        UriComponentsBuilder
                                .newInstance()
                                .path("/shelters/{id}")
                                .buildAndExpand(shelter.getId())
                                .toUri()
                );
        }
}
