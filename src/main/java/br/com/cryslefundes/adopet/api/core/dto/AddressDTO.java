package br.com.cryslefundes.adopet.api.core.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record AddressDTO(
        @NotBlank
        String address,
        @NotNull
        Integer number,
        String complement,
        @NotBlank
        String state,
        @NotBlank
        String city,
        @NotBlank
        @Pattern(regexp = "\\d{5,10}")
        @JsonAlias("zip_code")
        String zipCode
) {
}
