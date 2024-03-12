package br.com.cryslefundes.adopet.api.core.dto.tutors;

import br.com.cryslefundes.adopet.api.core.dto.AddressDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.UUID;

public record UpdateTutorDTO (
        @NotNull
        UUID id,
        String name,
        @Pattern(regexp = "\\(?\\d{2}\\)?\\d?\\d{4}-?\\d{4}")
        String phone,
        @Email
        String email,
        @Pattern(regexp = "\\\\d{3}\\\\.\\\\d{3}\\\\.\\\\d{3}\\\\-\\\\d{2}")
        String document,
        AddressDTO address
) {
}
