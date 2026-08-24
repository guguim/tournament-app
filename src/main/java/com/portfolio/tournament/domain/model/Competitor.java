package com.portfolio.tournament.domain.model;

import java.util.UUID;

/**
 * Entidade Rica que representa um jogador ou time.
 */
public class Competitor {
    private final UUID id;
    private String name;
    private Rating rating;

    // Construtor principal
    public Competitor(UUID id, String name, Rating rating) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("O nome do competidor é obrigatório.");
        }
        this.id = id;
        this.name = name;
        this.rating = rating != null ? rating : Rating.initial();
    }

    // Factory Method sem expor a criação do UUID para fora
    public static Competitor create(String name) {
        return new Competitor(UUID.randomUUID(), name, Rating.initial());
    }

    // Comportamento focado no domínio, não apenas um simples "setRating"
    public void updateRating(Rating newRating) {
        if (newRating == null) {
            throw new IllegalArgumentException("O novo rating não pode ser nulo.");
        }
        this.rating = newRating;
    }

    // Apenas Getters, não temos Setters públicos soltos
    public UUID getId() { return id; }
    public String getName() { return name; }
    public Rating getRating() { return rating; }
}
