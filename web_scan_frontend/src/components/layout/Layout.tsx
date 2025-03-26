import { Outlet } from "react-router-dom";
import { Box, Toolbar, useTheme } from "@mui/material";
import Navbar from "./navbar/Navbar"; // Includes both Sidebar & AppBar
import Footer from "./footer/Footer";

interface LayoutProps {
  children?: React.ReactNode;
}

export default function Layout({ children }: LayoutProps) {
  const theme = useTheme();
  const sidebarWidthWhenNotSmall = theme.spacing(30);

  return (
    <Box sx={{ display: "flex", flexDirection: "column", minHeight: "100vh" }}>
      <Navbar />
      {/* Main Content Area */}
      <Box
        component="main"
        sx={{
          flexGrow: 1,
          p: 3,
          ml: { md: sidebarWidthWhenNotSmall },
        }}
      >
        <Toolbar />
        {children || <Outlet />}
      </Box>
      <Footer />
    </Box>
  );
}
