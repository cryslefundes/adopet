package br.com.cryslefundes.adopet.api.core.entity;

import br.com.cryslefundes.adopet.api.core.enums.AdoptionStatus;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "adoptions")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Adoption {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private Instant dateOf;
    private String purpose;
    private String justification;
    @Enumerated(EnumType.STRING)
    private AdoptionStatus status;
    @ManyToOne(fetch = FetchType.LAZY)
    private Tutor tutor;
    @OneToOne(fetch = FetchType.LAZY)
    private Pet pet;

    public Adoption(Pet pet, Tutor tutor, String purpose) {
        this.pet = pet;
        this.tutor = tutor;
        this.purpose= purpose;
        this.dateOf = Instant.now();
        this.status = AdoptionStatus.WAITING_EVALUATION;
    }

    public void markAsApproved() {
        this.status = AdoptionStatus.APPROVED;
    }

    public void markAsDenied(String justification) {
        this.status = AdoptionStatus.DENIED;
        this.justification = justification;
    }
}
