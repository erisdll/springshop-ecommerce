# ============== WARNING!! ============================ WARNING!! ============================ WARNING!! ===============
# TO BE ABLE TO BUILD THIS IMAGE, YOU MUST SET THE ROOT OF THE MAIN PROJECT AS CONTEXT.
# IF RUNNING FROM IDE, EDIT THE RUN CONFIGURATION TO SET THE ROOT OF THE MAIN PROJECT AS CONTEXT.
# (i.e., the folder that contains the pom.xml file).
# ============== WARNING!! ============================ WARNING!! ============================ WARNING!! ===============

# == STAGE 1 - BUILD ===================================================================================================
FROM maven:3-eclipse-temurin-21 AS builder

WORKDIR /build

# Copia o wrapper e configuração Maven
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY services/gateway-service/pom.xml services/gateway-service/pom.xml
COPY services/gateway-service services/gateway-service

RUN chmod +x mvnw

# Baixa dependências e compila usando o wrapper
RUN mvn -f services/gateway-service/pom.xml dependency:go-offline
RUN mvn -f services/gateway-service/pom.xml clean package -DskipTests

# == STAGE 2 - RUNTIME =================================================================================================
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY --from=builder /build/services/gateway-service/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]