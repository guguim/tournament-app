package com.portfolio.tournament.domain.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Entidade que agrupa as partidas de uma fase/rodada específica do torneio.
 * (Ex: Quartas de Final, Semifinal).
 */
public class Round {
    private final int roundNumber;
    private final List<Match> matches;

    public Round(int roundNumber, List<Match> matches) {
        if (roundNumber <= 0) {
            throw new IllegalArgumentException("O número da rodada deve ser maior que zero.");
        }
        if (matches == null || matches.isEmpty()) {
            throw new IllegalArgumentException("Uma rodada deve conter pelo menos uma partida.");
        }
        this.roundNumber = roundNumber;
        this.matches = new ArrayList<>(matches);
    }

    public int getRoundNumber() { 
        return roundNumber; 
    }
    
    public List<Match> getMatches() { 
        return Collections.unmodifiableList(matches); 
    }
    
    /**
     * Regra de negócio: A rodada só está finalizada se TODAS as suas partidas
     * terminaram (seja por disputa normal ou W.O.).
     */
    public boolean isCompleted() {
        return matches.stream()
            .allMatch(m -> m.getStatus() == MatchStatus.COMPLETED || m.getStatus() == MatchStatus.WALKOVER);
    }
}
