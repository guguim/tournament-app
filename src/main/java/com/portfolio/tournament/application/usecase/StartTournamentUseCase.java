package com.portfolio.tournament.application.usecase;

import com.portfolio.tournament.domain.model.Tournament;
import com.portfolio.tournament.domain.repository.TournamentRepository;
import com.portfolio.tournament.domain.service.BracketGenerator;
import org.springframework.stereotype.Service;
import java.util.UUID;

/**
 * CASO DE USO (Application Service).
 * É o "Maestro" da Arquitetura Limpa. 
 * Ele não tem regras de negócio (matemática, if/else complexo), ele apenas orquestra.
 */
@Service
public class StartTournamentUseCase {

    // Dependemos sempre de INTERFACES (Portas e Domain Services), nunca de implementações.
    private final TournamentRepository tournamentRepository;
    private final BracketGenerator bracketGenerator;

    public StartTournamentUseCase(TournamentRepository tournamentRepository, BracketGenerator bracketGenerator) {
        this.tournamentRepository = tournamentRepository;
        this.bracketGenerator = bracketGenerator;
    }

    /**
     * Método central que executa a intenção do usuário.
     */
    public void execute(UUID tournamentId) {
        
        // PASSO 1: Busca a raiz de agregação (Aggregate Root) no banco de dados.
        Tournament tournament = tournamentRepository.findById(tournamentId)
            .orElseThrow(() -> new IllegalArgumentException("Torneio não encontrado."));

        // PASSO 2: Manda a entidade de domínio trabalhar (Regra de Negócio Pura).
        // Passamos o algoritmo que ela deve usar.
        tournament.start(bracketGenerator);

        // PASSO 3: Salva as alterações feitas no banco de dados.
        tournamentRepository.save(tournament);
    }
}
