import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import App from "./App.tsx";
import "bootstrap/dist/css/bootstrap.css";
import { BrowserRouter } from "react-router-dom";
import "./index.css";
import keycloak from './auth/keycloak';
import { ReactKeycloakProvider } from '@react-keycloak/web';

createRoot(document.getElementById("root")!).render(
  <ReactKeycloakProvider authClient={keycloak}>
  <StrictMode>
    <BrowserRouter>
      <App />
    </BrowserRouter>
  </StrictMode>
  </ReactKeycloakProvider>
);
