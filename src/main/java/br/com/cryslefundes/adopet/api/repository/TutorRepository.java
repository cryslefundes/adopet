package br.com.cryslefundes.adopet.api.repository;

import br.com.cryslefundes.adopet.api.core.entity.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TutorRepository extends JpaRepository<Tutor, UUID> {
}
