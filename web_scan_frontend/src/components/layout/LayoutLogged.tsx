import { Outlet, Navigate } from "react-router-dom";
import { loginPath } from "../../routes/routePaths";
import Loader from "../loader/Loader";
import UserLayout from "./Layout";
import { useKeycloak } from "@react-keycloak/web";
import { useState, useEffect } from "react";

const ProtectedRoute = () => {
  const { keycloak, initialized } = useKeycloak();
  const [isAuthenticated, setIsAuthenticated] = useState<boolean | null>(null);

  // Effect to handle authentication state safely
  useEffect(() => {
    if (initialized) {
      setIsAuthenticated((prev) => {
        if (prev !== keycloak.authenticated) {
          console.log("User ID:", keycloak.subject); // Logs only when authentication changes
        }
        return keycloak.authenticated ?? false;
      });
    }
  }, [initialized, keycloak.authenticated]);

  // Prevent rendering while Keycloak is initializing
  if (!initialized || isAuthenticated === null) {
    return <Loader />;
  }

  // If user is not authenticated, redirect to login **once**
  if (!isAuthenticated) {
    return <Navigate to={loginPath} replace />;
  }

  return (
    <UserLayout>
      <Outlet />
    </UserLayout>
  );
};

export default ProtectedRoute;
