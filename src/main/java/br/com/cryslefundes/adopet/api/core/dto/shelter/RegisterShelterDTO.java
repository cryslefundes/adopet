package br.com.cryslefundes.adopet.api.core.dto.shelter;

import br.com.cryslefundes.adopet.api.core.dto.AddressDTO;
import br.com.cryslefundes.adopet.api.core.entity.Address;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record RegisterShelterDTO(
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
        AddressDTO address
) {
}
