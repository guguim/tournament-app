package com.portfolio.tournament.domain.service;

import com.portfolio.tournament.domain.model.Competitor;
import com.portfolio.tournament.domain.model.Match;
import com.portfolio.tournament.domain.model.MatchStatus;
import com.portfolio.tournament.domain.model.Rating;

/**
 * Domain Service puro: Sem injeção de dependências externas (sem repositórios ou BD).
 * Pega os objetos em memória, aplica o cálculo matemático ELO e atualiza os próprios objetos.
 */
public class EloRatingService {

    // Fator K indica quão volátil é a mudança de pontuação.
    private static final int K_FACTOR = 32;

    public void applyRatingChanges(Match match) {
        // Se a partida não for COMPLETED (por exemplo, foi um WALKOVER), não alteramos o ELO!
        if (match.getStatus() != MatchStatus.COMPLETED) {
            return;
        }

        Competitor winner = match.getWinner()
            .orElseThrow(() -> new IllegalStateException("Uma partida concluída precisa ter um vencedor."));
        
        Competitor loser = winner.equals(match.getCompetitorOne()) ? match.getCompetitorTwo() : match.getCompetitorOne();

        int ratingWinner = winner.getRating().value();
        int ratingLoser = loser.getRating().value();

        // 1. Calcula a pontuação esperada (Probabilidade de vitória, 0.0 a 1.0)
        double expectedWinner = 1.0 / (1.0 + Math.pow(10, (ratingLoser - ratingWinner) / 400.0));
        double expectedLoser = 1.0 / (1.0 + Math.pow(10, (ratingWinner - ratingLoser) / 400.0));

        // 2. Calcula o novo rating. Pontuação real (S) é 1 para vitória e 0 para derrota.
        int newRatingWinner = (int) Math.round(ratingWinner + K_FACTOR * (1.0 - expectedWinner));
        int newRatingLoser = (int) Math.round(ratingLoser + K_FACTOR * (0.0 - expectedLoser));

        // 3. Atualiza as Entidades em memória
        winner.updateRating(new Rating(newRatingWinner));
        loser.updateRating(new Rating(newRatingLoser));
    }
}
