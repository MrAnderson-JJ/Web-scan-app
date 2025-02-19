import { useState } from "react";
import {
  AppBar,
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
  IconButton,
} from "@mui/material";
import {
  Menu,
  Logout,
  Home,
  Settings,
  History,
  Dashboard,
} from "@mui/icons-material";
import { Link } from "react-router-dom";
import AppNavBar from "../appBar/AppNavBar";

// Example user data
const user = {
  name: "John",
  sureName: "Doe",
  email: "johndoe@example.com",
};

const drawerWidth = 240;

// Example drawer items
const drawerItems = [
  { text: "Home", link: "/", icon: <Home /> },
  { text: "Dashboard", link: "/dashboard", icon: <Dashboard /> },
  { text: "History", link: "/history", icon: <History /> },
  { text: "Settings", link: "/settings", icon: <Settings /> },
];

const Sidebar = () => {
  const [smallOpen, setsmallOpen] = useState(false);

  // Toggle Drawer for small
  const handleDrawerToggle = () => {
    setsmallOpen(!smallOpen);
  };

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
          {user.name.charAt(0) + user.sureName.charAt(0)}
        </Avatar>
        <Typography variant="subtitle1" sx={{ mt: 1 }}>
          {user.name}
        </Typography>
        <Typography variant="caption" color="textSecondary">
          {user.email}
        </Typography>
        <Button
          variant="contained"
          color="error"
          startIcon={<Logout />}
          fullWidth
          sx={{ mt: 2 }}
          onClick={() => alert("Logging out...")}
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
