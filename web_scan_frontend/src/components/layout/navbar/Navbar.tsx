import { useState, useEffect } from "react";
import {
  Toolbar,
  Typography,
  Drawer,
  Box,
  List,
  ListItem,
  ListItemButton,
  ListItemIcon,
  ListItemText,
  Divider,
  Avatar,
  Button,
} from "@mui/material";
import {
  Logout,
  Home,
  History,
  Dashboard,
} from "@mui/icons-material";
import { Link } from "react-router-dom";
import AppNavBar from "../appBar/AppNavBar";
import { useKeycloak } from "@react-keycloak/web";

const drawerWidth = 240;

// Example drawer items
const drawerItems = [
  { text: "Home", link: "/", icon: <Home /> },
  { text: "Dashboard", link: "/dashboard", icon: <Dashboard /> },
  { text: "History", link: "/history", icon: <History /> },
];

const Sidebar = () => {
  const { keycloak } = useKeycloak();
  const [smallOpen, setsmallOpen] = useState(false);
  const [userName, setUserName] = useState("");

  // Toggle Drawer for small
  const handleDrawerToggle = () => {
    setsmallOpen(!smallOpen);
  };
  useEffect(() => {
    const loadUserProfile = async () => {
      if (keycloak?.authenticated) {
        const profile = await keycloak.loadUserProfile();
        setUserName(profile.username || "");
      }
    };

    loadUserProfile();
  }, [keycloak]);

  // Drawer Content (Same for all screen sizes)
  const drawerContent = (
    <>
      <Box sx={{ overflow: "auto" }}>
        <Toolbar />
        <List>
          {drawerItems.map((item) => (
            <ListItem key={item.text} disablePadding>
              <ListItemButton component={Link} to={item.link}>
                <ListItemIcon>{item.icon}</ListItemIcon>
                <ListItemText primary={item.text} />
              </ListItemButton>
            </ListItem>
          ))}
        </List>
        <Divider />
      </Box>

      {/* User Profile Section */}
      <Box sx={{ p: 2, textAlign: "center" }}>
        <Avatar
          sx={{ width: 56, height: 56, mx: "auto", bgcolor: "primary.main" }}
        >
          {userName.charAt(0).toUpperCase()}
        </Avatar>
        <Typography variant="subtitle1" sx={{ mt: 1 }}>
          {userName}
        </Typography>
        <Button
          variant="contained"
          color="error"
          startIcon={<Logout />}
          fullWidth
          sx={{ mt: 2 }}
          onClick={() => keycloak.logout({
            redirectUri: 'http://localhost:5173/login'
          })}
        >
          Logout
        </Button>
      </Box>
    </>
  );

  return (
    <Box sx={{ display: "flex" }}>
      {/* AppBar with Toggle Button for Small Screens */}
      <AppNavBar onMenuClick={handleDrawerToggle} />

      {/* Big Sidebar (Permanent) */}
      <Drawer
        variant="permanent"
        sx={{
          display: { xs: "none", md: "block" }, // Hide sidebar on small screens
          width: drawerWidth,
          flexShrink: 0,
          [`& .MuiDrawer-paper`]: {
            width: drawerWidth,
            boxSizing: "border-box",
            display: "flex",
            flexDirection: "column",
            justifyContent: "space-between",
          },
        }}
      >
        {drawerContent}
      </Drawer>

      {/* small Sidebar (Temporary) */}
      <Drawer
        variant="temporary"
        open={smallOpen}
        onClose={handleDrawerToggle}
        ModalProps={{ keepMounted: true }}
        sx={{
          display: { xs: "block", md: "none" }, // Show only on small screens
          [`& .MuiDrawer-paper`]: {
            width: "70%",
            margin: "auto",
            borderRadius: "10px",
            boxShadow: 3,
            display: "flex",
            flexDirection: "column",
            justifyContent: "space-between",
          },
        }}
      >
        {drawerContent}
      </Drawer>
    </Box>
  );
};

export default Sidebar;
