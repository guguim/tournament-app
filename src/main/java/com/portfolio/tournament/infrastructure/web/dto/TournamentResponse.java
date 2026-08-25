package com.portfolio.tournament.infrastructure.web.dto;

import com.portfolio.tournament.domain.model.Tournament;
import java.util.UUID;

/**
 * DTO de Saída.
 * Nunca devolvemos a Entidade do Domínio direto na API. Sempre a transformamos
 * em um DTO de resposta para evitar expor regras internas ou sofrer vazamento de dados.
 */
public record TournamentResponse(
    UUID id,
    String name,
    String status,
    int participantCount
) {
    public static TournamentResponse fromDomain(Tournament tournament) {
        return new TournamentResponse(
            tournament.getId(),
            tournament.getName(),
            tournament.getStatus().name(),
            tournament.getParticipants().size()
        );
    }
}
