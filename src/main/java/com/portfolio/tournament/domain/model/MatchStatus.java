package com.portfolio.tournament.domain.model;

public enum MatchStatus {
    SCHEDULED,    // Agendada
    IN_PROGRESS,  // Em andamento
    COMPLETED,    // Concluída (com disputa real)
    WALKOVER      // Concluída por W.O. (alguém faltou)
}
