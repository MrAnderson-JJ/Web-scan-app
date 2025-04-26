
FROM maven:3.9.6-eclipse-temurin-22 AS build

WORKDIR /build

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM openjdk:22

WORKDIR /app

COPY --from=build /build/target/output-service.jar output-service.jar

CMD ["java", "-jar", "output-service.jar"]


