-- V2: Criando a tabela para armazenar os Participantes (Competidores)

CREATE TABLE competitors (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    rating INT NOT NULL,
    tournament_id UUID NOT NULL,
    CONSTRAINT fk_tournament FOREIGN KEY (tournament_id) REFERENCES tournaments(id) ON DELETE CASCADE
);
