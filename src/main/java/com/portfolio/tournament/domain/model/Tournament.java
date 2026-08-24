package com.portfolio.tournament.domain.model;

import com.portfolio.tournament.domain.service.BracketGenerator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Aggregate Root (Raiz de Agregação).
 * Orquestra todo o ciclo de vida do Torneio. Qualquer mudança no torneio ou 
 * nas suas rodadas passa por essa classe, garantindo a consistência.
 */
public class Tournament {
    private final UUID id;
    private String name;
    private TournamentStatus status;
    private final List<Competitor> participants;
    private List<Round> rounds;

    public Tournament(UUID id, String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("O nome do torneio é obrigatório.");
        }
        this.id = id;
        this.name = name;
        this.status = TournamentStatus.DRAFT;
        this.participants = new ArrayList<>();
        this.rounds = new ArrayList<>();
    }

    public static Tournament create(String name) {
        return new Tournament(UUID.randomUUID(), name);
    }

    public void addParticipant(Competitor competitor) {
        if (this.status != TournamentStatus.DRAFT) {
            throw new IllegalStateException("Só é possível adicionar participantes antes de o torneio iniciar (DRAFT).");
        }
        if (competitor == null) {
            throw new IllegalArgumentException("Competidor não pode ser nulo.");
        }
        if (!participants.contains(competitor)) {
            participants.add(competitor);
        }
    }

    /**
     * O Método central onde a Mágica do Princípio da Inversão de Dependência (SOLID) acontece:
     * Recebemos a estratégia de geração (BracketGenerator) de fora (injetada). 
     * O Torneio apenas manda o gerador atuar, não precisando ter os loops e matemáticas complexas internamente.
     */
    public void start(BracketGenerator generator) {
        if (this.status != TournamentStatus.DRAFT) {
            throw new IllegalStateException("O torneio já foi iniciado ou concluído.");
        }
        if (this.participants.size() < 2) {
            throw new IllegalStateException("O torneio precisa de pelo menos 2 participantes para iniciar.");
        }
        
        // Delegação Limpa
        this.rounds = generator.generate(this.participants);
        this.status = TournamentStatus.IN_PROGRESS;
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public TournamentStatus getStatus() { return status; }
    public List<Competitor> getParticipants() { return Collections.unmodifiableList(participants); }
    public List<Round> getRounds() { return Collections.unmodifiableList(rounds); }
}
