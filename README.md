# SpringShop eCommerce

**SpringShop eCommerce** é um projeto de estudo que simula uma plataforma moderna de e-commerce construída com **Java 17**, **Spring Boot** e **microsserviços**. O objetivo é aplicar práticas avançadas de engenharia de software, com foco em arquitetura distribuída, mensageria assíncrona, cache distribuído, observabilidade e pipelines de CI/CD, utilizando **Docker** e **GitHub Actions**.

Este projeto serve como um ambiente de aprendizado prático, modular e escalável, com ênfase em boas práticas como **Clean Code**, **SOLID** e **Design Orientado a Domínio**.

## Estrutura do Projeto

   ```  
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

- Cada microserviço é um módulo Maven independente, empacotado com Docker.  
- Todos os serviços seguem o princípio de **Database per Service**, com diferentes tecnologias de persistência.  
- Um `docker-compose.yml` orquestra os serviços para execução local.  
   ```

## Tecnologias Utilizadas

- **Linguagem:** Java 21  
- **Frameworks:** Spring Boot, Spring Cloud, Spring Security, Spring JPA  
- **Build Tool:** Maven (estrutura multimódulo)  
- **Banco de Dados:** PostgreSQL, MongoDB  
- **Mensageria:** RabbitMQ  
- **Cache:** Redis  
- **Observabilidade:** Prometheus, Grafana  
- **Containerização:** Docker, Docker Compose  
- **CI/CD:** GitHub Actions  

## Estrutura Multimódulo e Pipeline Inteligente

### Maven Multimódulo e Docker

A estrutura multimódulo com Maven foi adotada para garantir **isolamento por domínio**, reuso de dependências comuns e modularidade no build. O desafio principal surgiu na **construção dos contêineres Docker**, pois:

- Cada `Dockerfile` de um módulo precisa acessar o `pom.xml` pai, localizado na raiz do projeto.
- Por padrão, o Docker não tem visibilidade fora do diretório atual durante o build.

**Solução implementada:**

- Os `Dockerfile`s foram ajustados para copiar o `pom.xml` pai explicitamente:

   ```
  COPY ../../pom.xml ../..  
  COPY . .  
   ```
- A build é executada com o contexto na raiz do projeto:

   ```
  docker build -f services/product-service/Dockerfile .  
   ```
Com isso, o Maven consegue resolver corretamente as dependências herdadas, e os builds continuam isolados por módulo.

### CI/CD Dinâmico com GitHub Actions

Um desafio ainda maior foi tornar a **integração contínua escalável**. Um pipeline único recompilando todos os módulos a cada commit seria ineficiente. A solução foi criar um **workflow inteligente que detecta quais módulos foram alterados** e aciona apenas os jobs relevantes.

**Principais estratégias:**

- Um job inicial verifica os diretórios alterados com:

   ``` 
  git diff --name-only origin/main  
   ```
- O output é processado por uma action customizada que identifica quais módulos Maven foram impactados.
- Cada job de build/test/deploy é ativado **condicionalmente**:

   ```
  if: contains(steps.changed_files.outputs.modules, 'product-service')  
   ```
   
Isso reduziu o tempo de execução das pipelines e tornou o processo mais escalável e eficiente, com deploys direcionados por módulo.

## 📦 Serviços Principais

| Serviço                | Responsabilidade                                    | Banco de Dados |
|------------------------|-----------------------------------------------------|----------------|
| `gateway-service`      | Autenticação, segurança e roteamento                | —              |
| `product-service`      | Catálogo e gerenciamento de produtos                | MongoDB        |
| `order-service`        | Processamento e rastreamento de pedidos             | PostgreSQL     |
| `inventory-service`    | Controle de estoque e sincronização de quantidade   | PostgreSQL     |
| `payment-service`      | Integração com gateway de pagamentos                | PostgreSQL     |
| `notification-service` | Envio de e-mails e mensagens                        | MongoDB        |
| `user-service`         | Registro, login e gerenciamento de perfis           | PostgreSQL     |

Todos os serviços utilizam **Redis** para cache e **RabbitMQ** para comunicação assíncrona.

## Observabilidade

Cada microserviço expõe métricas via **Spring Boot Actuator**, coletadas pelo **Prometheus**. A visualização é feita através de **Grafana**, com dashboards personalizados para:

- Latência por serviço  
- Uso de CPU/memória  
- Erros HTTP e exceções  
- Quantidade de requisições  

## Como Executar Localmente

### Pré-requisitos

- Java 21  
- Maven  
- Docker + Docker Compose  

### Passos

1. Clone o repositório:

   CÓDIGO  
   git clone https://github.com/erisdll/springshop-ecommerce.git  
   cd springshop-ecommerce  

2. Suba os serviços:

   ```
   docker-compose up --build  
   ```

## Contribuições

Contribuições são bem-vindas! Antes de abrir um PR, por favor abra uma issue para discutirmos as mudanças propostas. Feedbacks técnicos são encorajados.

---

## Licença

Este projeto está licenciado sob a **Licença MIT**. Consulte o arquivo `LICENSE` para mais detalhes.
