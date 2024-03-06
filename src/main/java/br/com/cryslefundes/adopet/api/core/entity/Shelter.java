package br.com.cryslefundes.adopet.api.core.entity;


import br.com.cryslefundes.adopet.api.core.dto.shelter.RegisterShelterDTO;
import br.com.cryslefundes.adopet.api.core.dto.shelter.UpdateShelterDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "shelters")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "pets")
public class Shelter {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String phone;
    private String email;
    @Embedded
    private Address address;
    @OneToMany(mappedBy = "shelter")
    private List<Pet> pets;

    public Shelter(RegisterShelterDTO dto) {
        this.name = dto.name();
        this.phone = dto.phone();
        this.email = dto.email();
        this.address = new Address(dto.address());
    }

    public void updateInfo(UpdateShelterDTO dto) {
        if (dto.name() != null) this.name = dto.name();
        if (dto.phone() != null) this.phone = dto.phone();
        if (dto.email() != null) this.email = dto.email();
        if (dto.address() != null) this.address.updateAddress(dto.address());
    }
}
