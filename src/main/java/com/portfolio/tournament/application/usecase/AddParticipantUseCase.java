package com.portfolio.tournament.application.usecase;

import com.portfolio.tournament.domain.model.Competitor;
import com.portfolio.tournament.domain.model.Tournament;
import com.portfolio.tournament.domain.repository.TournamentRepository;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class AddParticipantUseCase {

    private final TournamentRepository repository;

    public AddParticipantUseCase(TournamentRepository repository) {
        this.repository = repository;
    }

    public void execute(UUID tournamentId, String competitorName) {
        // 1. Busca
        Tournament tournament = repository.findById(tournamentId)
            .orElseThrow(() -> new IllegalArgumentException("Torneio não encontrado."));
            
        // 2. Delega (Domínio cuida da regra "SÓ PODE ADICIONAR SE ESTIVER EM DRAFT")
        Competitor competitor = Competitor.create(competitorName);
        tournament.addParticipant(competitor);
        
        // 3. Salva
        repository.save(tournament);
    }
}
