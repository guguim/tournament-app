package com.portfolio.tournament.infrastructure.config;

import com.portfolio.tournament.domain.service.BracketGenerator;
import com.portfolio.tournament.domain.service.SingleEliminationGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Classe de configuração da Infraestrutura.
 * Funciona como a "Ponte" entre o Domínio Puro e o Spring Boot.
 */
@Configuration
public class DomainConfig {

    /**
     * Instancia manualmente a nossa classe pura do domínio e diz para o Spring:
     * "Tome, guarde esse objeto. Sempre que alguém pedir um BracketGenerator, entregue ele!"
     */
    @Bean
    public BracketGenerator bracketGenerator() {
        return new SingleEliminationGenerator();
    }
}
