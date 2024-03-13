package br.com.cryslefundes.adopet.api.core.entity;

import br.com.cryslefundes.adopet.api.core.dto.AddressDTO;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Getter
public class Address {
    private String address;
    private int number;
    private String complement;
    private String state;
    private String city;
    private String zipCode;

    public Address(AddressDTO address) {
        this.address = address.address();
        this.number = address.number();
        this.complement = address.complement();
        this.state = address.state();
        this.city = address.city();
        this.zipCode = address.zipCode();
    }

    public void updateAddress(AddressDTO dto) {
        if (dto.address() != null) this.address = dto.address();
        if (dto.number() != null) this.number = dto.number();
        if (dto.complement() != null) this.complement = dto.complement();
        if (dto.state() != null) this.state = dto.state();
        if (dto.city() != null) this.city = dto.city();
        if (dto.zipCode() != null) this.zipCode = dto.zipCode();
    }
}
