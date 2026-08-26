# Tournament Engine API 🏆

Uma API robusta e escalável para gerenciamento de torneios e geração de chaves (brackets), construída com **Java 21** e **Spring Boot 3**.

Este projeto foi desenvolvido com foco em **Qualidade de Software**, aplicando rigorosamente os conceitos de **Clean Architecture (Arquitetura Hexagonal)**, **Domain-Driven Design (DDD)** e **SOLID**. O objetivo principal é manter a lógica de negócios completamente isolada de frameworks e banco de dados.

## 🚀 Tecnologias e Ferramentas

- **Linguagem:** Java 21
- **Framework:** Spring Boot 3.3.0
- **Persistência:** Spring Data JPA, Hibernate, PostgreSQL
- **Migrações de Banco:** Flyway
- **Tratamento de Erros:** Problem Details (RFC 7807)
- **Validação:** Jakarta Bean Validation
- **Infraestrutura:** Docker e Docker Compose

## 🏗️ Arquitetura e Padrões de Projeto

O projeto foi dividido em camadas estritas para garantir o isolamento do Domínio:

1. **Domain (Núcleo):** Contém as Regras de Negócio puras (Entidades Ricas, Value Objects, Aggregates). Não possui nenhuma dependência do Spring ou do Banco de Dados.
2. **Application (Use Cases):** Orquestra as regras de negócio. Responsável por buscar dados, chamar o domínio e salvar o estado.
3. **Infrastructure (Adaptadores):** Implementa as interfaces do domínio (Portas). Aqui vivem os Controllers (REST), Repositórios (JPA) e as configurações de Injeção de Dependência do Spring.

### 💡 Padrões Aplicados
- **Strategy Pattern:** Utilizado na geração de chaves (`BracketGenerator`), permitindo que a lógica de chaveamento seja injetada no domínio via *Dependency Inversion Principle (DIP)*.
- **Aggregate Root:** A entidade `Tournament` atua como raiz de agregação, garantindo a consistência das inscrições e do ciclo de vida das rodadas.
- **Global Exception Handling:** Centralização de erros com o formato padronizado RFC 7807, garantindo respostas de erro claras e consistentes para os clientes da API.

## ⚙️ Como Executar o Projeto

### Pré-requisitos
- [Java 21](https://jdk.java.net/21/)
- [Docker](https://www.docker.com/) e Docker Compose
- Maven (via IDE ou CLI)

### Passos

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/tournament-app.git
   cd tournament-app
   ```

2. Suba o banco de dados PostgreSQL localmente via Docker:
   ```bash
   docker-compose up -d
   ```

3. Execute a aplicação (O Flyway criará as tabelas do banco automaticamente). Você pode rodar pela sua IDE (como IntelliJ ou VS Code) na classe `TournamentApplication.java`, ou via terminal:
   ```bash
   mvn spring-boot:run
   ```

## 🔌 API Endpoints

Abaixo estão os endpoints disponíveis. Existe também um arquivo `requests.http` na raiz do projeto para você testar nativamente na IDE.

### 1. Criar Torneio
`POST /api/v1/tournaments`
```json
{
  "name": "Mortal Kombat Championship 2026"
}
```

### 2. Adicionar Participante
`POST /api/v1/tournaments/{tournamentId}/participants`
```json
{
  "competitorName": "Scorpion"
}
```

### 3. Iniciar Torneio (Gerar Chaveamento)
`POST /api/v1/tournaments/{tournamentId}/start`
*(Não requer body. Inicie apenas após ter ao menos 2 participantes adicionados ao torneio).*

---

*Desenvolvido com dedicação para o meu portfólio de Engenharia de Software.*
