# Web Scan App

Aplikace pro skenování sítě mikroservisní architekturou pomocí Nmapu.

## Požadavky

Nutno mít nainstalované:

- [Docker Desktop](https://www.docker.com/products/docker-desktop/)
- [Java JDK 22+](https://adoptium.net/)
- [Node.js](https://nodejs.org/) (doporučeno LTS)

## Jak aplikaci spustit

### 1. Naklonovat repozitář:

```bash
git clone https://github.com/MrAnderson-JJ/Web-scan-app
cd web-scan-app
```

### 2. Spustit aplikaci (jen Windows):

        V adresáři web-scan-app spustit start.bat

        Tento skript spustí:

        Docker infrastrukturu (MongoDB, Keycloak)

        Všechny Spring Boot mikroslužby

        Frontend s npm install && npm run dev (včetně stažení závislostí)

### 3. Otevřít frontend v prohlížeči:
        http://localhost:5173

        Uživatelské přihlašovací údaje:
        Username: test
        Password: test


        Přihlášení do Keycloaku
        Admin konzole: http://localhost:8181

        Realm: network-scanner

        Admin účet:
        Username: admin

        Password: admin