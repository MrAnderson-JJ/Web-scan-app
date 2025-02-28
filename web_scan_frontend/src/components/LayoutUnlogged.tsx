import { Outlet, Navigate, useLocation } from "react-router-dom";
import Loader from "../components/loader/Loader";
import { Box, Toolbar } from "@mui/material";
import AppNavBar from "./layout/appBar/AppNavBar";
import Footer from "./layout/footer/Footer";
import { rootPath, loginPath } from "../routes/routePaths";
import { useKeycloak } from "@react-keycloak/web";
import { useState, useEffect } from "react";

export default function Layout() {
  const { keycloak, initialized } = useKeycloak();
  const [isAuthenticated, setIsAuthenticated] = useState<boolean | null>(null);
  const location = useLocation(); // Get the current route

  // Effect to handle authentication state safely
  useEffect(() => {
    if (initialized) {
      setIsAuthenticated(keycloak.authenticated ?? false);
    }
  }, [initialized, keycloak.authenticated]);

  // Show loader while Keycloak is initializing
  if (!initialized || isAuthenticated === null) {
    return <Loader />;
  }

  // 🚨 **Fix Infinite Redirects**
  // - Only redirect when the user is on `/login` and they are authenticated
  if (isAuthenticated && location.pathname === loginPath) {
    return <Navigate to={rootPath} replace />;
  }

  return (
    <Box sx={{ display: "flex", flexDirection: "column", minHeight: "100vh" }}>
      <AppNavBar />
      <Box
        component="main"
        sx={{
          flexGrow: 1,
          p: 3,
        }}
      >
        <Toolbar />
        <Outlet />
      </Box>
      <Footer />
    </Box>
  );
}
