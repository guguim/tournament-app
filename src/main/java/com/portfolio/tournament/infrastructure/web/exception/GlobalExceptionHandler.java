package com.portfolio.tournament.infrastructure.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;

/**
 * Interceptador Global de Exceções.
 * Implementa o padrão RFC 7807 (Problem Details) de forma nativa com o Spring Boot 3.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Intercepta qualquer IllegalArgumentException lançada por qualquer parte do sistema
     * (Domínio, Use Case, Controller) e transforma em um JSON 400 Bad Request bem formatado.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ProblemDetail handleIllegalArgumentException(IllegalArgumentException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problemDetail.setTitle("Requisição Inválida");
        problemDetail.setType(URI.create("https://api.tournament.com/errors/bad-request"));
        return problemDetail;
    }
    
    /**
     * Intercepta regras de negócio quebradas (ex: Torneio já iniciado) e retorna 409 Conflict.
     */
    @ExceptionHandler(IllegalStateException.class)
    public ProblemDetail handleIllegalStateException(IllegalStateException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
        problemDetail.setTitle("Conflito de Regra de Negócio");
        problemDetail.setType(URI.create("https://api.tournament.com/errors/conflict"));
        return problemDetail;
    }
}
