package com.portfolio.tournament.infrastructure.persistence.entity;

import com.portfolio.tournament.domain.model.TournamentStatus;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tournaments")
public class TournamentJpaEntity {
    
    @Id
    private UUID id;
    
    private String name;
    
    @Enumerated(EnumType.STRING)
    private TournamentStatus status;

    // A MÁGICA AQUI: Um torneio tem VÁRIOS competidores
    // CascadeType.ALL = Ao salvar o Torneio, salve os competidores também!
    @OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CompetitorJpaEntity> participants = new ArrayList<>();

    protected TournamentJpaEntity() {}

    public TournamentJpaEntity(UUID id, String name, TournamentStatus status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public TournamentStatus getStatus() { return status; }
    public List<CompetitorJpaEntity> getParticipants() { return participants; }
}
