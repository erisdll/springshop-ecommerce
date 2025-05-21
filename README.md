# SpringShop eCommerce

SpringShop Commerce é um projeto de estudo que simula uma plataforma de e-commerce desenvolvida com Java e Spring Boot. O objetivo é aplicar conceitos modernos de arquitetura de microsserviços, mensageria assíncrona, cache distribuído, observabilidade, pipelines de CI/CD, containerização e orquestração com Docker.

A proposta deste projeto é servir como ambiente de aprendizado prático, modular e escalável, com ênfase em boas práticas de engenharia de software.

## Arquitetura e Estrutura

- Microserviços modulares para cada domínio (produto, pedido, inventário etc.)
- Spring Boot com estrutura multi-módulo via Maven
- Contêineres Docker individuais para facilitar o desenvolvimento local
- Integração opcional com front-end (a ser desenvolvida posteriormente)
- Database per service: cada microserviço gerencia sua própria base de dados.

```bash
springshop-ecommerce/
├── services/
│   ├── gateway-service/
│   ├── inventory-service/
│   ├── notification-service/
│   ├── order-service/
│   ├── payment-service/
│   ├── product-service/
│   └── user-service/
├── prometheus/
├── .github/
├── .mvn/
├── docker-compose.yml
├── pom.xml
└── README.md
```

## Serviços Principais

- **Serviço de Gateway**: segurança do sistema e roteamento das requisições
- **Serviço de Pedidos**: processamento e rastreamento de pedidos
- **Serviço de Produtos**: gerenciamento de catálogo de produtos
- **Serviço de Inventário**: controle de estoque
- **Serviço de Pagamento**: integração com gateways de pagamento
- **Serviço de Notificações**: envio de e-mails e mensagens
- **Serviço de Usuários**: cadastro, autenticação e gerenciamento de perfis

## Persistência, Mensageria e Observabilidade

Cada serviço possui seu próprio banco de dados, garantindo isolamento e escalabilidade:

- Serviço de Produtos: PostgreSQL para armazenar dados estruturados dos produtos.
- Serviço de Pedidos: PostgreSQL para dados transacionais dos pedidos.
- Serviço de Inventário: MongoDB para dados não estruturados do estoque.
- Serviço de Usuários: PostgreSQL para informações de usuário.
- Serviço de Notificações: MongoDB para documentos de e-mails e mensagens.

Todos os serviços utilizam Redis para caching.

Mensageria entre serviços que requerem comunicação assíncrona é feita com RabbitMQ.

Para observabilidade, todos os serviços estão integrados com Prometheus e Grafana:

- Prometheus: coleta métricas de desempenho via endpoints `actuator/prometheus`, incluindo CPU, memória, latência, uso de threads e outras métricas de aplicação.
- Grafana: dashboards visuais para monitoramento do sistema.

## Tecnologias Utilizadas

- **Linguagem:** Java 17
- **Frameworks:** Spring Boot/Web/Cloud/Security e JPA
- **Build Tool:** Maven
- **Banco de Dados:** PostgreSQL, MongoDB
- **Cache:** Redis
- **Mensageria:** RabbitMQ
- **Monitoramento:** Prometheus, Grafana
- **Containerização:** Docker, Docker Compose
- **CI/CD (em desenvolvimento):** GitHub Actions

## Como executar

Pré-requisitos

- Java 17 ou superior
- Docker e Docker Compose
- Maven

1. Clone o repositório:

```bash
git clone https://github.com/erisdll/springshop-ecommerce.git
cd springshop-ecommerce
```

2. Suba os serviços com Docker Compose:
```bash
docker-compose up
```

## Contribuição
Contribuições são bem-vindas. Antes de enviar um pull request, abra uma issue para discutirmos possíveis mudanças.

## Licença
Este projeto está licenciado sob a Licença MIT.
