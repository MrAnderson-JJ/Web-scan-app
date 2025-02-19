import React, { useState } from "react";
import { TextField, Button, Box } from "@mui/material";

interface ScanFormProps {
  onSubmit: (scanId: string) => void;
}

const ScanForm = ({ onSubmit }: ScanFormProps) => {
  const [scanId, setScanId] = useState("");

  const handleSubmit = (event: React.FormEvent) => {
    event.preventDefault();
    if (scanId.trim()) {
      onSubmit(scanId);
    }
  };

  return (
    <Box 
      component="form" 
      onSubmit={handleSubmit} 
      sx={{ display: "flex", gap: 2, alignItems: "center", mb: 3 }}
    >
      <TextField
        label="Scan ID"
        variant="outlined"
        value={scanId}
        onChange={(e) => setScanId(e.target.value)}
        required
      />
      <Button type="submit" variant="contained" color="primary">
        Load Scan
      </Button>
    </Box>
  );
};

export default ScanForm;
