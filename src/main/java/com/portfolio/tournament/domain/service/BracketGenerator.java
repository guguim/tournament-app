package com.portfolio.tournament.domain.service;

import com.portfolio.tournament.domain.model.Competitor;
import com.portfolio.tournament.domain.model.Round;
import java.util.List;

/**
 * Interface Domain Service (Strategy Pattern).
 * Define o contrato para qualquer algoritmo de chaveamento.
 * Assim, o Torneio não fica acoplado a uma única forma de gerar partidas.
 */
public interface BracketGenerator {
    
    /**
     * Gera as rodadas a partir da lista de inscritos.
     */
    List<Round> generate(List<Competitor> participants);
}
