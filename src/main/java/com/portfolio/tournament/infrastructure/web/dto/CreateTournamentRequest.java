package com.portfolio.tournament.infrastructure.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO (Data Transfer Object) usando Records do Java 21.
 * O Bean Validation (@NotBlank, @Size) garante que o Controller rejeite 
 * requisições ruins automaticamente, devolvendo 400 Bad Request.
 */
public record CreateTournamentRequest(
    @NotBlank(message = "O nome do torneio é obrigatório.")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres.")
    String name
) {}
