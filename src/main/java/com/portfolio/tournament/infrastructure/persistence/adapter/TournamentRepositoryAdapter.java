package com.portfolio.tournament.infrastructure.persistence.adapter;

import com.portfolio.tournament.domain.model.Tournament;
import com.portfolio.tournament.domain.repository.TournamentRepository;
import com.portfolio.tournament.infrastructure.persistence.entity.TournamentJpaEntity;
import com.portfolio.tournament.infrastructure.persistence.repository.SpringDataTournamentRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

/**
 * ADAPTADOR (Adapter) - Implementa a Porta do Domínio.
 * Essa classe faz a "tradução" entre o mundo puro (Domínio) e o mundo do Banco de Dados (JPA).
 */
@Repository
public class TournamentRepositoryAdapter implements TournamentRepository {

    private final SpringDataTournamentRepository springRepository;

    public TournamentRepositoryAdapter(SpringDataTournamentRepository springRepository) {
        this.springRepository = springRepository;
    }

    @Override
    public void save(Tournament tournament) {
        // 1. Mapeamento de Domínio Puro -> JPA Entity
        TournamentJpaEntity jpaEntity = new TournamentJpaEntity(
            tournament.getId(),
            tournament.getName(),
            tournament.getStatus()
        );
        
        // 2. Salva usando o Spring Data
        springRepository.save(jpaEntity);
    }

    @Override
    public Optional<Tournament> findById(UUID id) {
        // 1. Busca usando o Spring Data
        return springRepository.findById(id)
            .map(jpa -> {
                // 2. Mapeamento Reverso: JPA Entity -> Domínio Puro
                Tournament domainTournament = new Tournament(jpa.getId(), jpa.getName());
                // (Nota didática: em um cenário completo, faríamos o mapeamento de todos os atributos, rounds, etc)
                return domainTournament;
            });
    }
}
