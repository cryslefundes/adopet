package br.com.cryslefundes.adopet.api.core.entity;

import br.com.cryslefundes.adopet.api.core.dto.tutors.RegisterTutorDTO;
import br.com.cryslefundes.adopet.api.core.dto.tutors.UpdateTutorDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tutors")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Tutor {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String phone;
    private String email;
    private String document;
    @Embedded
    private Address address;
    private boolean active;
    @OneToMany(mappedBy = "tutor")
    private List<Adoption> adoptions;

    public Tutor(RegisterTutorDTO dto) {
        this.name = dto.name();
        this.phone = dto.phone();
        this.email = dto.email();
        this.document = dto.document();
        this.address = new Address(dto.address());
        this.active = true;
    }

    public void updateInfo(UpdateTutorDTO dto) {
        if (dto.name() != null) this.name = dto.name();
        if (dto.phone() != null) this.phone = dto.phone();
        if (dto.email() != null) this.email = dto.email();
        if (dto.document() != null) this.document = dto.document();
        if (dto.address() != null) this.address.updateAddress(dto.address());
    }
}
