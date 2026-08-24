# 🏆 Tournament & Matchmaking Engine

> Uma API RESTful robusta e de alta performance desenvolvida em **Java 21** e **Spring Boot 3**, projetada para gerenciar torneios competitivos, geração dinâmica de chaveamentos e cálculo de ranking de competidores via **Algoritmo ELO**.

---

## 📌 Sobre o Projeto

O **Tournament & Matchmaking Engine** foi construído com foco em boas práticas de engenharia de software, separação clara de responsabilidades e desacoplamento de regras de negócio. O projeto implementa princípios de **Clean Architecture (Arquitetura Hexagonal)**, garantindo que algoritmos críticos (como chaveamento de partidas e cálculo de rating) permaneçam puros, testáveis e independentes de frameworks.

### Principais Funcionalidades:
- **Gestão de Competidores:** Cadastro, controle de status e histórico de pontuação.
- **Cálculo de Rating ELO:** Atualização em tempo real do ranking após cada partida com base na probabilidade matemática de vitória e resultado consolidado.
- **Criação e Gestão de Torneios:** Suporte a formatos eliminatórios (Single Elimination) com distribuição por *seeds* (cabeças de chave).
- **Geração Automática de Chaves:** Algoritmo dinâmico que estrutura rodadas e confrontos a partir dos inscritos.
- **Avanço de Fases:** Processamento de resultados com validação de regras de negócio e avanço automático de competidores na árvore.
- **Auditoria e Snapshots:** Histórico imutável de confrontos e evolução temporal do ELO.

---

## 🏗️ Arquitetura e Estrutura

O projeto adota a **Clean Architecture**, dividida em camadas bem delineadas:

```text
src/main/java/com/engine/tournament/
├── domain/                    # Núcleo de negócio puro (sem dependências de frameworks)
│   ├── model/                 # Entidades e Value Objects (Competitor, Tournament, Match, ELO)
│   └── service/               # Algoritmos puros (EloCalculator, BracketGenerator)
├── application/               # Casos de uso e orquestração da aplicação
│   ├── usecase/               # Implementação dos fluxos de negócio
│   └── port/                  # Interfaces de entrada (Inbound) e saída (Outbound)
│       ├── in/                # Interfaces de casos de uso
│       └── out/               # Interfaces de persistência e eventos
├── infrastructure/            # Implementações técnicas e integrações
│   ├── adapter/               # Adaptadores de persistência (JPA Repositories, Mappers, Entidades JPA)
│   ├── configuration/         # Configurações do Spring (Beans, OpenAPI, CORS)
│   └── exception/             # Handlers globais (RFC 7807 Problem Details)
└── presentation/              # Camada de entrada externa
    ├── controller/            # Endpoints REST Controllers
    └── dto/                   # DTOs de Request/Response (Java 21 Records)
```

---

## 🧮 Algoritmo de Ranking ELO

A atualização do rating de dois competidores $A$ e $B$ segue a fórmula matemática padrão:

1. **Cálculo da Expectativa de Vitória ($E$):**
   $$E_A = \frac{1}{1 + 10^{(R_B - R_A) / 400}}$$
   $$E_B = \frac{1}{1 + 10^{(R_A - R_B) / 400}}$$

2. **Atualização do Rating ($R'$):**
   $$R'_A = R_A + K \cdot (S_A - E_A)$$
   $$R'_B = R_B + K \cdot (S_B - E_B)$$

*Onde $K$ é o fator de ajuste (default: 32), $R$ é a pontuação atual e $S$ é a pontuação real da partida ($1.0$ para vitória, $0.0$ para derrota).*

---

## 🛠️ Tecnologias e Ferramentas

- **Linguagem:** Java 21 (LTS) — *Records, Pattern Matching, Sealed Types*
- **Framework:** Spring Boot 3.x (Spring Web, Spring Data JPA, Validation)
- **Banco de Dados:** PostgreSQL
- **Migrações:** Flyway Database Migrations
- **Documentação da API:** OpenAPI 3 / Swagger (SpringDoc)
- **Testes Automatizados:** JUnit 5, Mockito, AssertJ e **Testcontainers** (PostgreSQL real em container)
- **Containerização:** Docker e Docker Compose

---

## 🚀 Como Executar o Projeto

### Pré-requisitos
- **Java 21 JDK** instalado
- **Docker** e **Docker Compose** instalados
- **Maven** 3.9+ (ou utilizar o `./mvnw` embutido)

### 1. Clonar o repositório
```bash
git clone https://github.com/seu-usuario/tournament-engine.git
cd tournament-engine
```

### 2. Subir o ambiente com Docker
```bash
docker compose up -d
```
*Isso inicializará a instância do banco de dados PostgreSQL configurada e pronta para receber as migrações do Flyway.*

### 3. Executar a aplicação
```bash
./mvnw spring-boot:run
```
A API estará acessível em: `http://localhost:8080`

### 4. Executar os testes automatizados
```bash
# Executa testes unitários e de integração (via Testcontainers)
./mvnw clean test
```

---

## 📖 Documentação dos Endpoints (Swagger)

Com a aplicação em execução, acesse a interface interativa do Swagger UI:
- **Swagger UI:** `http://localhost:8080/swagger-ui.html`
- **OpenAPI JSON:** `http://localhost:8080/v3/api-docs`

### Principais Endpoints:

| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| `POST` | `/api/v1/competitors` | Cadastra um novo competidor (inicia com rating base) |
| `GET` | `/api/v1/competitors/{id}` | Consulta detalhes e histórico de rating do competidor |
| `POST` | `/api/v1/tournaments` | Cria um novo torneio |
| `POST` | `/api/v1/tournaments/{id}/generate-bracket` | Gera o chaveamento automático do torneio |
| `GET` | `/api/v1/tournaments/{id}/bracket` | Retorna o estado atual da árvore de confrontos |
| `POST` | `/api/v1/matches/{id}/result` | Registra o resultado da partida, avança chave e atualiza ELO |

---

## 🧪 Estratégia de Testes

- **Testes Unitários:** Foco estrito em regras de domínio e cálculos isolados (`EloCalculatorTest`, `BracketGeneratorTest`).
- **Testes de Integração:** Execução de fluxos completos de persistência e casos de uso com **Testcontainers**, garantindo paridade total de ambiente com o PostgreSQL de produção.

---

## 👤 Autor

Desenvolvido por **Hugo Santos**  
- [GitHub](https://github.com/)
- [LinkedIn](https://linkedin.com/)
