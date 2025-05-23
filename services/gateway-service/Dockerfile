# ============== WARNING!! ============================ WARNING!! ============================ WARNING!! ===============
# TO BE ABLE TO BUILD THIS IMAGE, YOU MUST SET THE ROOT OF THE MAIN PROJECT AS CONTEXT.
# IF RUNNING FROM IDE, EDIT THE RUN CONFIGURATION TO SET THE ROOT OF THE MAIN PROJECT AS CONTEXT.
# (i.e., the folder that contains the pom.xml file).
# ============== WARNING!! ============================ WARNING!! ============================ WARNING!! ===============

# == STAGE 1 - BUILD ===================================================================================================
FROM maven:3-eclipse-temurin-21 AS builder

WORKDIR /build

#copia o mvnw da raiz do projeto
COPY mvnw .
#copia a pasta .mvn da raiz do projeto
COPY .mvn .mvn
#copia o pom.xml da raiz do projeto
COPY pom.xml .
#copia o modulo
COPY services/gateway-service services/gateway-service

#permite a execução do mvnw
RUN chmod +x mvnw
# Baixa dependências e compila
RUN mvn -f services/gateway-service/pom.xml clean package -DskipTests dependency:go-offline

# == STAGE 2 - RUNTIME =================================================================================================
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

#copia o jar gerado na etapa de build
COPY --from=builder /build/services/gateway-service/target/*.jar app.jar
EXPOSE 8080
# Define o ponto de entrada do container
ENTRYPOINT ["java", "-jar", "app.jar"]