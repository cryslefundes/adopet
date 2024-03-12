package br.com.cryslefundes.adopet.api.core.entity;

import br.com.cryslefundes.adopet.api.core.dto.pet.RegisterPetDTO;
import br.com.cryslefundes.adopet.api.core.dto.pet.UpdatePetDTO;
import br.com.cryslefundes.adopet.api.core.enums.PetType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "pets")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pet {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String breed;
    private Integer age;
    private Boolean adopted;
    private Float weight;
    @Enumerated(EnumType.STRING)
    private PetType type;
    @OneToOne(mappedBy = "pet", fetch = FetchType.LAZY)
    private Adoption adoption;
    @ManyToOne(fetch = FetchType.LAZY)
    private Shelter shelter;

    public Pet(RegisterPetDTO dto) {
        this.name = dto.name();
        this.breed = dto.breed();
        this.age = dto.age();
        this.adopted = false;
        this.weight = getWeight();
        this.type = dto.type();
    }

    public void updateInfo(UpdatePetDTO dto) {
        if (dto.name() != null) this.name = dto.name();
        if (dto.age() != null) this.age = dto.age();
        if (dto.type() != null) this.type = dto.type();
        if (dto.breed() != null) this.breed = dto.breed();
        if (dto.weight() != null) this.weight = dto.weight();
    }

    public void markAsAdopted() {
        this.adopted = true;
    }
}
