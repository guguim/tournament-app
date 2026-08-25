package com.portfolio.tournament.infrastructure.persistence.adapter;

import com.portfolio.tournament.domain.model.Competitor;
import com.portfolio.tournament.domain.model.Rating;
import com.portfolio.tournament.domain.model.Tournament;
import com.portfolio.tournament.domain.repository.TournamentRepository;
import com.portfolio.tournament.infrastructure.persistence.entity.CompetitorJpaEntity;
import com.portfolio.tournament.infrastructure.persistence.entity.TournamentJpaEntity;
import com.portfolio.tournament.infrastructure.persistence.repository.SpringDataTournamentRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class TournamentRepositoryAdapter implements TournamentRepository {

    private final SpringDataTournamentRepository springRepository;

    public TournamentRepositoryAdapter(SpringDataTournamentRepository springRepository) {
        this.springRepository = springRepository;
    }

    @Override
    public void save(Tournament tournament) {
        TournamentJpaEntity jpaEntity = new TournamentJpaEntity(
            tournament.getId(),
            tournament.getName(),
            tournament.getStatus()
        );
        
        // MAPEAMENTO DOS FILHOS: Copiamos da Entidade Pura para a Entidade do Banco
        List<CompetitorJpaEntity> jpaCompetitors = tournament.getParticipants().stream()
            .map(c -> new CompetitorJpaEntity(
                c.getId(), 
                c.getName(), 
                c.getRating().value(), 
                jpaEntity))
            .collect(Collectors.toList());
            
        jpaEntity.getParticipants().addAll(jpaCompetitors);
        
        springRepository.save(jpaEntity);
    }

    @Override
    public Optional<Tournament> findById(UUID id) {
        return springRepository.findById(id)
            .map(jpa -> {
                Tournament domainTournament = new Tournament(jpa.getId(), jpa.getName());
                
                // MAPEAMENTO REVERSO: Copiamos da Entidade do Banco de volta para a Pura
                for (CompetitorJpaEntity cJpa : jpa.getParticipants()) {
                    Competitor competitor = new Competitor(cJpa.getId(), cJpa.getName(), new Rating(cJpa.getRating()));
                    domainTournament.addParticipant(competitor);
                }
                
                return domainTournament;
            });
    }
}
