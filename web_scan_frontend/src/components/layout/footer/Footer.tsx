import { Box, Typography } from "@mui/material";

const Footer = () => {
  return (
    <Box
      component="footer"
      sx={{
        width: "100%",
        position: "relative", // Keeps footer at the bottom
        bottom: 0,
        mt: "auto", // Pushes footer to bottom
        py: 2,
        textAlign: "center",
        backgroundColor: "primary.main",
        color: "white",
      }}
    >
      <Typography variant="body2">
        © {new Date().getFullYear()} Web Scan App. All rights reserved.
      </Typography>
    </Box>
  );
};

export default Footer;