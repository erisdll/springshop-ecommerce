# ===========================
# STAGE 1 - BUILD
# ===========================
FROM maven:3-eclipse-temurin-21 AS builder

WORKDIR /build

COPY pom.xml .
COPY common/common-domain ./common/common-domain
COPY common/common-security ./common/common-security
COPY services/user-service ./services/user-service

RUN mvn -f services/user-service/pom.xml dependency:go-offline

RUN mvn -f services/user-service/pom.xml clean package -DskipTests

# ===========================
# STAGE 2 - RUNTIME
# ===========================
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY --from=builder /build/services/user-service/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]