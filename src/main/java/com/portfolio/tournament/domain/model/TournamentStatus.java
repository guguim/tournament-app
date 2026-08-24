package com.portfolio.tournament.domain.model;

public enum TournamentStatus {
    DRAFT,          // Recebendo inscrições (ainda permite adicionar participantes)
    IN_PROGRESS,    // Chaveamento gerado e partidas ocorrendo
    COMPLETED       // Torneio finalizado
}
