import React, { useState } from "react";
import {
  TextField,
  Button,
  Box,
  FormControl,
  InputLabel,
  Select,
  MenuItem,
} from "@mui/material";
import { startScanFromOutput } from "../../api/scanApi";
import { ScanTypes, ScanTypeLabels } from "@/types/scan/ScanType";
import { useKeycloak } from "@react-keycloak/web";


interface ScanFormProps {
  onSubmit: (webSocketId: string) => void;
}

const ScanFormIp = ({ onSubmit }: ScanFormProps) => {
  const [scanId, setScanId] = useState("");
  const [scanFlags, setScanFlags] = useState("");
  const [scanOption, setScanOption] = useState(ScanTypes.SCAN_PING);
  const { keycloak } = useKeycloak();

  const handleSubmit = async (event: React.FormEvent) => {
    event.preventDefault();
    try {
      console.log(scanId, scanFlags);
      const flags: string[] = scanFlags.split(",");
      console.log(flags);
      if (!keycloak.subject) {
        throw new Error("Keycloak subject is not set");
      }
      console.log(onSubmit(await startScanFromOutput(scanId, keycloak.subject, flags, scanOption)));
      onSubmit(await startScanFromOutput(scanId, keycloak.subject, flags, scanOption));
    } catch (error) {
      console.error("Error starting scan:", error);
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
      <TextField
        label="Scan Flags"
        variant="outlined"
        value={scanFlags}
        onChange={(e) => setScanFlags(e.target.value)}
        required
      />
      
      {/* Dropdown with human-readable labels */}
      <FormControl variant="outlined" required sx={{ minWidth: 200 }}>
        <InputLabel>Scan Option</InputLabel>
        <Select
          value={scanOption}
          onChange={(e) => setScanOption(e.target.value as ScanTypes)}
          label="Scan Option"
        >
          {Object.values(ScanTypes).map((type) => (
            <MenuItem key={type} value={type}>
              {ScanTypeLabels[type]}
            </MenuItem>
          ))}
        </Select>
      </FormControl>

      <Button type="submit" variant="contained" color="primary">
        Start Scan
      </Button>
    </Box>
  );
};

export default ScanFormIp;