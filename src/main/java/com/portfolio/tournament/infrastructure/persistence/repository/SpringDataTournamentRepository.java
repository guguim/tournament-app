package com.portfolio.tournament.infrastructure.persistence.repository;

import com.portfolio.tournament.infrastructure.persistence.entity.TournamentJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

/**
 * Interface do Spring Data JPA.
 * Fica estritamente na Infraestrutura. O Domínio nem sabe que isso existe.
 */
public interface SpringDataTournamentRepository extends JpaRepository<TournamentJpaEntity, UUID> {
}
