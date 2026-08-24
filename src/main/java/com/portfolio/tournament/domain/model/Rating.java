package com.portfolio.tournament.domain.model;

/**
 * Value Object (Objeto de Valor) representando o Rating ELO de um competidor.
 * O uso de 'record' (Java 14+) garante que o objeto seja imutável e nos isenta de 
 * escrever equals(), hashCode() e toString() manualmente.
 */
public record Rating(int value) {

    public Rating {
        if (value < 0) {
            // Regra de negócio na construção do VO: um rating nunca pode ser negativo.
            throw new IllegalArgumentException("O rating não pode ser negativo.");
        }
    }

    /**
     * Factory method para o rating inicial padrão.
     */
    public static Rating initial() {
        return new Rating(1000); // 1000 é nossa base ELO inicial
    }
}
