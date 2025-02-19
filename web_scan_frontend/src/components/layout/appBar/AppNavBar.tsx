import { AppBar, Toolbar, Typography, IconButton } from "@mui/material";
import { Menu } from "@mui/icons-material";

interface NavbarProps {
  onMenuClick?: () => void; // Function to toggle small sidebar
  hasButton?: boolean; // Show button on the left
}

const Navbar = ({ onMenuClick, hasButton = true }: NavbarProps) => {
  return (
    <AppBar
      position="fixed"
      sx={{
        zIndex: (theme) => theme.zIndex.drawer + 1,
        backgroundColor: "#2E3B55",
      }}
    >
      <Toolbar>
        {hasButton && (
          <IconButton
            color="inherit"
            edge="start"
            onClick={onMenuClick}
            sx={{ display: { md: "none" }, mr: 2 }} // Hidden on md+
          >
            <Menu />
          </IconButton>
        )}
        <Typography variant="h6" noWrap>
          Web Scan App
        </Typography>
      </Toolbar>
    </AppBar>
  );
};

export default Navbar;
