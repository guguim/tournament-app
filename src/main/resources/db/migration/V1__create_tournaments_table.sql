-- V1: Script inicial de criação da tabela de Torneios
-- O Flyway executará isso automaticamente quando o Spring Boot iniciar

CREATE TABLE tournaments (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    status VARCHAR(50) NOT NULL
);
