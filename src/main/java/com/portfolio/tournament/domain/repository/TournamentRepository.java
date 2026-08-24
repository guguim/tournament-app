package com.portfolio.tournament.domain.repository;

import com.portfolio.tournament.domain.model.Tournament;
import java.util.Optional;
import java.util.UUID;

/**
 * PORTA (Port) - Fica dentro do pacote de Domínio.
 * O Domínio diz o que ele precisa, mas não dita como será feito.
 */
public interface TournamentRepository {
    void save(Tournament tournament);
    Optional<Tournament> findById(UUID id);
}
