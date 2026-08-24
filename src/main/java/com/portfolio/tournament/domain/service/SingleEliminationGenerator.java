package com.portfolio.tournament.domain.service;

import com.portfolio.tournament.domain.model.Competitor;
import com.portfolio.tournament.domain.model.Match;
import com.portfolio.tournament.domain.model.Round;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementação do BracketGenerator para torneios de Eliminação Simples (Mata-Mata).
 */
public class SingleEliminationGenerator implements BracketGenerator {

    @Override
    public List<Round> generate(List<Competitor> participants) {
        if (participants == null || participants.size() < 2) {
            throw new IllegalArgumentException("São necessários no mínimo 2 participantes para gerar uma chave.");
        }
        
        // Simplicidade didática: para eliminação simples direta, exigimos que o total de 
        // participantes seja uma potência de 2 (ex: 2, 4, 8, 16). 
        // Em um sistema real super complexo, criaríamos "Byes" (avanço automático) para quem sobrou.
        if (!isPowerOfTwo(participants.size())) {
             throw new IllegalArgumentException("Para este formato restrito, o número de participantes deve ser potência de 2.");
        }

        List<Round> rounds = new ArrayList<>();
        List<Match> firstRoundMatches = new ArrayList<>();

        // Gera a PRIMEIRA rodada pareando jogadores adjacentes na lista.
        // Se a lista estiver ordenada por ELO (Seeding), teremos confrontos baseados no ranking.
        for (int i = 0; i < participants.size(); i += 2) {
            Competitor p1 = participants.get(i);
            Competitor p2 = participants.get(i + 1);
            firstRoundMatches.add(Match.schedule(p1, p2));
        }

        rounds.add(new Round(1, firstRoundMatches));
        
        return rounds;
    }

    private boolean isPowerOfTwo(int n) {
        return (n > 0) && ((n & (n - 1)) == 0);
    }
}
