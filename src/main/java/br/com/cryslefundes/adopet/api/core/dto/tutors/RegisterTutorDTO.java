package br.com.cryslefundes.adopet.api.core.dto.tutors;

import br.com.cryslefundes.adopet.api.core.dto.AddressDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record RegisterTutorDTO (
        @NotBlank
        String name,
        @NotBlank
        @Pattern(regexp = "\\(?\\d{2}\\)?\\d?\\d{4}-?\\d{4}", message = "Phone number format invalid")
        String phone,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "Please, request like this: 000.000.000-00")
        String document,
        @NotNull
        @Valid
        AddressDTO address
) {
}
