package com.portfolio.tournament.infrastructure.persistence.entity;

import com.portfolio.tournament.domain.model.TournamentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;

/**
 * JPA Entity - Fica na Infraestrutura.
 * O único propósito desta classe é mapear colunas do banco relacional.
 * Sem regras de negócio aqui!
 */
@Entity
@Table(name = "tournaments")
public class TournamentJpaEntity {
    
    @Id
    private UUID id;
    
    private String name;
    
    @Enumerated(EnumType.STRING)
    private TournamentStatus status;

    // Construtor sem argumentos obrigatório para o framework JPA/Hibernate
    protected TournamentJpaEntity() {}

    public TournamentJpaEntity(UUID id, String name, TournamentStatus status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public TournamentStatus getStatus() { return status; }
}
