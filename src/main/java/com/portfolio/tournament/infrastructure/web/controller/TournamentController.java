package com.portfolio.tournament.infrastructure.web.controller;

import com.portfolio.tournament.application.usecase.AddParticipantUseCase;
import com.portfolio.tournament.application.usecase.CreateTournamentUseCase;
import com.portfolio.tournament.application.usecase.StartTournamentUseCase;
import com.portfolio.tournament.domain.model.Tournament;
import com.portfolio.tournament.infrastructure.web.dto.AddParticipantRequest;
import com.portfolio.tournament.infrastructure.web.dto.CreateTournamentRequest;
import com.portfolio.tournament.infrastructure.web.dto.TournamentResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tournaments")
public class TournamentController {

    private final CreateTournamentUseCase createTournamentUseCase;
    private final AddParticipantUseCase addParticipantUseCase;
    private final StartTournamentUseCase startTournamentUseCase;

    public TournamentController(
            CreateTournamentUseCase createTournamentUseCase,
            AddParticipantUseCase addParticipantUseCase,
            StartTournamentUseCase startTournamentUseCase) {
        this.createTournamentUseCase = createTournamentUseCase;
        this.addParticipantUseCase = addParticipantUseCase;
        this.startTournamentUseCase = startTournamentUseCase;
    }

    @PostMapping
    public ResponseEntity<TournamentResponse> createTournament(@Valid @RequestBody CreateTournamentRequest request) {
        Tournament tournament = createTournamentUseCase.execute(request.name());
        return ResponseEntity.status(HttpStatus.CREATED).body(TournamentResponse.fromDomain(tournament));
    }

    @PostMapping("/{id}/participants")
    public ResponseEntity<Void> addParticipant(
            @PathVariable UUID id,
            @Valid @RequestBody AddParticipantRequest request) {
        
        addParticipantUseCase.execute(id, request.competitorName());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/{id}/start")
    public ResponseEntity<Void> startTournament(@PathVariable UUID id) {
        startTournamentUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
