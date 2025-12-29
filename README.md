# Tech Challenge Payments

API de pagamentos desenvolvida em Java com Spring Boot.

## Sumário

- [Descrição](#descrição)
- [Tecnologias](#tecnologias)
- [Como executar](#como-executar)
- [Endpoints principais](#endpoints-principais)
- [Testes](#testes)
- [Coleção Postman](#coleção-postman)
- [Licença](#licença)

## Descrição

Este projeto implementa uma API REST para gerenciamento de ordens de pagamento, consulta de status e integração com métodos de pagamento como Pix.

## Tecnologias

- Java 17+
- Spring Boot
- MongoDB
- Maven
- Swagger/OpenAPI
- JUnit
- Cucumber (BDD)

## Como executar

1. Clone o repositório:
   ```bash
   git clone <url-do-repositorio>
   cd tech-challenge-payments
   ```

2. Compile o projeto:
   ```bash
   mvn clean install
   ```

3. Execute a aplicação:
   ```bash
   mvn spring-boot:run
   ```

4. Acesse a documentação Swagger:
   ```
   http://localhost:8080/swagger-ui.html
   ```

## Endpoints principais

- **Criar ordem de pagamento**
  - `POST /api/payments`
- **Consultar status do pagamento**
  - `GET /api/payments/{paymentId}/status`

## Testes

Para rodar os testes automatizados:
```bash
mvn test
```

## Coleção Postman

A coleção de testes está disponível em `postman/Tech_Challenge_API.postman_collection.json`.  
Importe no Postman e utilize a variável `baseUrl` para facilitar a execução dos endpoints.

