package com.portfolio.tournament.application.usecase;

import com.portfolio.tournament.domain.model.Tournament;
import com.portfolio.tournament.domain.repository.TournamentRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateTournamentUseCase {

    private final TournamentRepository repository;

    public CreateTournamentUseCase(TournamentRepository repository) {
        this.repository = repository;
    }

    public Tournament execute(String name) {
        // 1. O Domínio é o responsável por saber COMO criar um torneio novo (Factory method)
        Tournament tournament = Tournament.create(name);
        
        // 2. A Porta diz para o adaptador de infraestrutura salvar
        repository.save(tournament);
        
        return tournament;
    }
}
