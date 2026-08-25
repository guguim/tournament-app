package com.portfolio.tournament.infrastructure.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.UUID;

/**
 * Entidade JPA para mapear a tabela de Competidores.
 */
@Entity
@Table(name = "competitors")
public class CompetitorJpaEntity {

    @Id
    private UUID id;
    
    private String name;
    
    private int rating;

    // Relacionamento Inverso: Muitos Competidores pertencem a Um Torneio
    @ManyToOne
    @JoinColumn(name = "tournament_id", nullable = false)
    private TournamentJpaEntity tournament;

    protected CompetitorJpaEntity() {}

    public CompetitorJpaEntity(UUID id, String name, int rating, TournamentJpaEntity tournament) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.tournament = tournament;
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public int getRating() { return rating; }
    public TournamentJpaEntity getTournament() { return tournament; }
}
