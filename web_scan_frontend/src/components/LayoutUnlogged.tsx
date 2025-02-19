import { Outlet } from "react-router-dom";
import { Box, Toolbar } from "@mui/material";
import AppNavBar from "./layout//appBar/AppNavBar";
import Footer from "./layout/footer/Footer";

export default function Layout() {
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
