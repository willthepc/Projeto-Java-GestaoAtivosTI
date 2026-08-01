# Gestão de Ativos

API REST desenvolvida em Spring Boot para o controle de equipamentos (ativos de TI) vinculados a funcionários de uma empresa. Projeto de estudo desenvolvido durante estágio, com foco em arquitetura em camadas, JPA/Hibernate, boas práticas de API REST e segurança básica de configuração.

## 🚀 Tecnologias

- **Java 25**
- **Spring Boot 4.0.0**
  - Spring Web (MVC)
  - Spring Data JPA
- **H2 Database** (perfil de testes, em memória)
- **Lombok**
- **springdoc-openapi** (Swagger UI)
- **Maven**

![Java](https://img.shields.io/badge/Java-25-orange?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.0-6DB33F?logo=springboot&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-build-C71A36?logo=apachemaven&logoColor=white)
![License](https://img.shields.io/badge/license-MIT-blue)

## 📐 Arquitetura

O projeto segue uma arquitetura em camadas:

```
com.projeto.gestaoAtivos
├── entities/       # Entidades JPA (Funcionario, Equipamento)
├── dto/            # Objetos de transferência de dados (entrada/saída da API)
├── repositories/   # Interfaces Spring Data JPA
├── services/       # Regras de negócio
├── resources/      # Controllers REST
├── exception/      # Exceções customizadas e modelo de resposta de erro
├── handler/        # Tratamento global de exceções (@RestControllerAdvice)
└── config/         # Configurações (ex: carga inicial de dados para testes)
```

## 🗃️ Modelo de dados

- **Funcionario**: `id`, `name`, `department`, `email` e uma lista de equipamentos vinculados (`@OneToMany`).
- **Equipamento**: `id`, `model`, `type` e um funcionário vinculado (`@ManyToOne`).

**Regra de negócio**: um funcionário não pode ter mais de um equipamento do mesmo `type` (ex: dois "Notebook"). Essa validação é aplicada tanto na criação quanto na atualização de um equipamento — inclusive ao trocar o `type` de um equipamento existente ou reatribuí-lo a outro funcionário.

## 📌 Endpoints

### Funcionários

| Método | Endpoint              | Descrição                          |
|--------|------------------------|-------------------------------------|
| GET    | `/funcionarios`        | Lista todos os funcionários         |
| GET    | `/funcionarios/{id}`   | Busca um funcionário por id         |
| POST   | `/funcionarios`        | Cria um novo funcionário            |
| PUT    | `/funcionarios/{id}`   | Atualiza um funcionário existente   |
| DELETE | `/funcionarios/{id}`   | Remove um funcionário               |

### Equipamentos

| Método | Endpoint              | Descrição                                   |
|--------|------------------------|-----------------------------------------------|
| GET    | `/equipamentos`        | Lista todos os equipamentos                    |
| GET    | `/equipamentos/{id}`   | Busca um equipamento por id                    |
| POST   | `/equipamentos`        | Cria um novo equipamento vinculado a um funcionário |
| PUT    | `/equipamentos/{id}`   | Atualiza um equipamento existente              |
| DELETE | `/equipamentos/{id}`   | Remove um equipamento                          |

## ⚠️ Tratamento de erros

A API centraliza o tratamento de exceções via `GlobalExceptionHandler`, retornando um corpo padronizado (`ErrorResponse`) com `message` e `status`:

| Situação                                                             | Status HTTP |
|------------------------------------------------------------------------|:-----------:|
| Funcionário ou equipamento não encontrado                              | 404 Not Found |
| Funcionário já possui equipamento do mesmo tipo (criação ou atualização) | 409 Conflict |
| Tentativa de deletar registro com vínculo (integridade referencial)      | 409 Conflict |
| Erro interno não mapeado                                                | 500 Internal Server Error |

### Documentação da API (Swagger)

Com a dependência `springdoc-openapi` incluída, a documentação interativa fica disponível em:

```
http://localhost:8080/swagger-ui.html
```
