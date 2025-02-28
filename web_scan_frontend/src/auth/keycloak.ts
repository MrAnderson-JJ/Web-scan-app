import Keycloak from 'keycloak-js';

const keycloak = new Keycloak({
  url: 'http://localhost:8181', // URL Keycloak serveru
  realm: 'network-scanner', // Název realmu, který jsi vytvořil v Keycloak
  clientId: 'frontend-client', // ID klienta pro frontend
});

export default keycloak;
