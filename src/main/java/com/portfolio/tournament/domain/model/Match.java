package com.portfolio.tournament.domain.model;

import java.util.Optional;
import java.util.UUID;

/**
 * Entidade que representa uma partida no torneio.
 */
public class Match {
    private final UUID id;
    private final Competitor competitorOne;
    private final Competitor competitorTwo;
    private Competitor winner;
    private MatchStatus status;

    public Match(UUID id, Competitor competitorOne, Competitor competitorTwo) {
        if (competitorOne == null || competitorTwo == null) {
            throw new IllegalArgumentException("Uma partida exige dois competidores.");
        }
        if (competitorOne.equals(competitorTwo)) {
            throw new IllegalArgumentException("Um competidor não pode jogar contra si mesmo.");
        }
        
        this.id = id;
        this.competitorOne = competitorOne;
        this.competitorTwo = competitorTwo;
        this.status = MatchStatus.SCHEDULED;
    }

    public static Match schedule(Competitor one, Competitor two) {
        return new Match(UUID.randomUUID(), one, two);
    }

    /**
     * Finaliza uma partida com uma vitória tradicional.
     */
    public void finishMatch(Competitor winnerCompetitor) {
        validateFinish(winnerCompetitor);
        this.winner = winnerCompetitor;
        this.status = MatchStatus.COMPLETED;
    }

    /**
     * Finaliza a partida explicitamente como W.O.
     * Isso nos permite diferenciar na hora do cálculo do ELO!
     */
    public void finishByWalkover(Competitor winnerCompetitor) {
        validateFinish(winnerCompetitor);
        this.winner = winnerCompetitor;
        this.status = MatchStatus.WALKOVER;
    }

    private void validateFinish(Competitor winnerCompetitor) {
        if (this.status == MatchStatus.COMPLETED || this.status == MatchStatus.WALKOVER) {
            throw new IllegalStateException("A partida já foi finalizada.");
        }
        if (!winnerCompetitor.equals(competitorOne) && !winnerCompetitor.equals(competitorTwo)) {
            throw new IllegalArgumentException("O vencedor deve ser um dos competidores desta partida.");
        }
    }

    public UUID getId() { return id; }
    public Competitor getCompetitorOne() { return competitorOne; }
    public Competitor getCompetitorTwo() { return competitorTwo; }
    public Optional<Competitor> getWinner() { return Optional.ofNullable(winner); }
    public MatchStatus getStatus() { return status; }
}
