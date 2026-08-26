package com.portfolio.tournament.infrastructure.web.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class TournamentControllerIT {

    // SOBE UM BANCO DE DADOS REAL NO DOCKER APENAS PARA OS TESTES!
    // O @ServiceConnection injeta as credenciais (URL, usuario, senha) automaticamente no Spring!
    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine");

    // Simulador de requisições HTTP (Semelhante ao Postman)
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldCreateTournamentSuccessfully() {
        // Arrange: Preparar os dados simulando o body JSON
        String requestJson = """
            {
              "name": "Testcontainers Championship"
            }
            """;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(requestJson, headers);

        // Act: Disparar a requisição contra a nossa própria API (que subiu em uma porta aleatória)
        ResponseEntity<String> response = restTemplate.postForEntity("/api/v1/tournaments", request, String.class);

        // Assert: Verificar se o status foi 201 Created e se os dados estão corretos no retorno
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).contains("Testcontainers Championship");
        assertThat(response.getBody()).contains("DRAFT");
    }
}
