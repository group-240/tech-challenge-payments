# Tech Challenge - Payments

Repositório responsável pelo microserviço de pagamentos.
Integração com Mercado Pago

## O que este repositório faz

- **API de Pagamentos** - Processamento de pagamentos via `/api/payment`
- **Integração MercadoPago** - Webhook de confirmação
- **Deployment K8s** - Deploy no EKS via Terraform
- **Health Check** - Actuator em `/api/actuator/health`

## Dependências

| Dependência | Descrição |
|-------------|-----------|
| tech-challenge-infra | EKS Cluster e ECR (via remote state) |
| tech-challenge-dynamoDB | Tabelas DynamoDB (via remote state) |
| Terraform >= 1.10.0 | Ferramenta de IaC |
| Java 17 | Runtime da aplicação |
| Maven | Build da aplicação |

## Secrets Necessários (GitHub)

- `AWS_ACCESS_KEY_ID`
- `AWS_SECRET_ACCESS_KEY`
