package br.com.cryslefundes.adopet.api.repository;

import br.com.cryslefundes.adopet.api.core.entity.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ShelterRepository extends JpaRepository<Shelter, UUID> {
}
