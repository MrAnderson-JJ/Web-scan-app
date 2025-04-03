# Java 17 (případně jinou podle toho, co máš v projektu)
FROM openjdk:21-jdk

# Nastav pracovní adresář
WORKDIR /app

# Zkopíruj JAR soubor
COPY target/*.jar app.jar

# Spusť aplikaci
ENTRYPOINT ["java", "-jar", "app.jar"]