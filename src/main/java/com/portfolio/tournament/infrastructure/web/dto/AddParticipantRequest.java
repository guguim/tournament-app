package com.portfolio.tournament.infrastructure.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AddParticipantRequest(
    @NotBlank(message = "O nome do competidor é obrigatório.")
    @Size(min = 2, max = 50, message = "O nome deve ter entre 2 e 50 caracteres.")
    String competitorName
) {}
