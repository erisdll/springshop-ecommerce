# ============== WARNING!! ============================ WARNING!! ============================ WARNING!! ===============
# TO BE ABLE TO BUILD THIS IMAGE, YOU MUST SET THE ROOT OF THE MAIN PROJECT AS CONTEXT.
# IF RUNNING FROM IDE, EDIT THE RUN CONFIGURATION TO SET THE ROOT OF THE MAIN PROJECT AS CONTEXT.
# (i.e., the folder that contains the pom.xml file).
# ============== WARNING!! ============================ WARNING!! ============================ WARNING!! ===============

# == STAGE 1 - BUILD ===================================================================================================
FROM maven:3-eclipse-temurin-21 AS builder

WORKDIR /build

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY services/payment-service services/payment-service

RUN chmod +x mvnw
RUN mvn -f services/payment-service/pom.xml clean package -DskipTests dependency:go-offline

# == STAGE 2 - RUNTIME =================================================================================================
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY --from=builder /build/services/payment-service/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]