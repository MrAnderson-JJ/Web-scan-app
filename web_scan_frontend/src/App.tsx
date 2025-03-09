import { useState, useEffect } from "react";
import AllRoutesComponent from "./routes/AllRoutesComponent";
import { useKeycloak } from "@react-keycloak/web";
import { saveUserIfNotExists } from "./api/userApi";

export default function App() {
  const { keycloak, initialized } = useKeycloak();
  useEffect(() => {
    const saveUser = async () => {
      if (keycloak.authenticated && initialized) {
        try {
          if (keycloak.subject) {
            await saveUserIfNotExists(keycloak.subject);
            console.log("User saved");
          }
        } catch (err) {
          console.error(err);
        }
      }
    };
    saveUser();
  }, [keycloak.authenticated, initialized]);

  return (
    <div className="App">
      <AllRoutesComponent />
    </div>
  );
}
