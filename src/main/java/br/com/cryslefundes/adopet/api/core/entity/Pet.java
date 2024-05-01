package br.com.cryslefundes.adopet.api.core.entity;

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
    private PetType petType;
    @OneToOne(mappedBy = "pet", fetch = FetchType.LAZY)
    private Adoption adoption;
    @ManyToOne(fetch = FetchType.LAZY)
    private Shelter shelter;

    public Pet(String name, String breed, Integer age, Float weight, String type, Shelter shelter) {
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.adopted = false;
        this.weight = weight;
        this.shelter = shelter;
        this.petType = PetType.fromString(type.toUpperCase().trim());
    }

    public void updateInfo(UpdatePetDTO dto) {
        if (dto.name() != null) this.name = dto.name();
        if (dto.age() != null) this.age = dto.age();
        if (dto.petType() != null) this.petType = dto.petType();
        if (dto.breed() != null) this.breed = dto.breed();
        if (dto.weight() != null) this.weight = dto.weight();
    }

    public void markAsAdopted() {
        this.adopted = true;
    }
}
