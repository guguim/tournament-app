package com.portfolio.tournament.infrastructure.web.controller;

import com.portfolio.tournament.application.usecase.StartTournamentUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

/**
 * Adaptador de Entrada (Primary Adapter).
 * Recebe as requisições da web (HTTP/REST) e traduz para a linguagem do Domínio (através do Use Case).
 */
@RestController
@RequestMapping("/api/v1/tournaments")
public class TournamentController {

    private final StartTournamentUseCase startTournamentUseCase;

    // O Spring se encarrega de injetar o Use Case aqui
    public TournamentController(StartTournamentUseCase startTournamentUseCase) {
        this.startTournamentUseCase = startTournamentUseCase;
    }

    /**
     * Endpoint para iniciar um torneio.
     * Exemplo de chamada: POST /api/v1/tournaments/123e4567-e89b-12d3-a456-426614174000/start
     */
    @PostMapping("/{id}/start")
    public ResponseEntity<Void> startTournament(@PathVariable UUID id) {
        
        // Repare que o Controller é "burro". Ele não faz cálculos,
        // não usa if/else para regras de negócio. Ele apenas delega!
        startTournamentUseCase.execute(id);
        
        // Retorna status 204 (No Content) indicando sucesso sem corpo na resposta.
        return ResponseEntity.noContent().build();
    }
}
