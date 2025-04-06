FROM eclipse-temurin:21-jdk

RUN apt-get update && apt-get install -y nmap && rm -rf /var/lib/apt/lists/*

WORKDIR /app

COPY target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]