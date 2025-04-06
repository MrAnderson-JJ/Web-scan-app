@echo off

REM Spustit zbytek v Dockeru
docker-compose up -d

REM Spustit user-service
start cmd /k "cd user-service && mvnw spring-boot:run"

REM Spustit output-service
start cmd /k "cd output-service && mvnw spring-boot:run"

REM Spustit scan-service
start cmd /k "cd scan-service && mvnw spring-boot:run"

REM Spustit api-gateway
start cmd /k "cd api-gateway && mvnw spring-boot:run"

REM Spustit frontend
start cmd /k "cd web_scan_frontend && npm run dev"
