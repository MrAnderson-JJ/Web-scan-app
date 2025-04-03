import React, { useState } from "react";
import {
  TextField,
  Button,
  Box,
  FormControl,
  InputLabel,
  Select,
  MenuItem,
  FormControlLabel,
  Switch,
} from "@mui/material";
import { startScanFromOutput } from "../../api/scanApi";
import { ScanTypes, ScanTypeLabels } from "@/types/scan/scanCommand/ScanType";
import {
  ScanTiming,
  scanTimingLabels,
} from "@/types/scan/scanCommand/ScanTiming";
import { useKeycloak } from "@react-keycloak/web";

interface ScanFormProps {
  onSubmit: (webSocketId: string) => void;
}

const ScanFormIp = ({ onSubmit }: ScanFormProps) => {
  const [scanId, setScanId] = useState("");
  const [scanTiming, setScanTiming] = useState(ScanTiming.T3);
  const [scanOption, setScanOption] = useState(ScanTypes.SCAN_QUICK);
  const [portRange, setPortRange] = useState("");
  const [internalNetwork, setInternalNetwork] = useState(false);
  const { keycloak } = useKeycloak();

  const handleSubmit = async (event: React.FormEvent) => {
    event.preventDefault();

    if (
      !isValidScanId(scanId) ||
      (!isValidPortRange(portRange) && scanOption !== ScanTypes.SCAN_PING)
    ) {
      return;
    }

    let portRangeFlag = "";
    if (scanOption === ScanTypes.SCAN_PING) {
      portRangeFlag = "";
    } else if (portRange === "") {
      portRangeFlag = "";
    } else {
      portRangeFlag = "p " + portRange;
    }
    try {
      console.log(scanTiming);
      if (!keycloak.subject) {
        throw new Error("Keycloak subject is not set");
      }
      console.log(
        onSubmit(
          await startScanFromOutput(
            scanId,
            keycloak.subject,
            scanTiming,
            scanOption,
            portRangeFlag
          )
        )
      );
    } catch (error) {
      console.error("Error starting scan:", error);
    }
  };

  const isValidScanId = (value: string): boolean => {
    if (scanOption === ScanTypes.SCAN_PING || internalNetwork) return true;
    return !value.includes(",") && !value.includes("/");
  };

  return (
    <Box
      component="form"
      onSubmit={handleSubmit}
      sx={{ display: "flex", gap: 2, alignItems: "center", mb: 3 }}
    >
      <FormControlLabel
        control={
          <Switch
            checked={internalNetwork}
            onChange={(e) => setInternalNetwork(e.target.checked)}
            color="primary"
          />
        }
        label="Internal network"
      />

      <TextField
        label="Scan ID (IP address)"
        variant="outlined"
        value={scanId}
        onChange={(e) => setScanId(e.target.value)}
        required
        error={!isValidScanId(scanId)}
        helperText={
          !isValidScanId(scanId)
            ? "Only a single IP allowed unless Internal network is enabled or scan is Ping"
            : ""
        }
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

      {scanOption !== ScanTypes.SCAN_PING && (
        <FormControl variant="outlined" required sx={{ minWidth: 250 }}>
          <InputLabel>Scan timing</InputLabel>
          <Select
            value={scanTiming}
            onChange={(e) => setScanTiming(e.target.value as ScanTiming)}
            label="Scan timing"
            renderValue={(selected) =>
              `${scanTimingLabels[selected as ScanTiming].label} (${selected})`
            }
          >
            {Object.values(ScanTiming).map((type) => (
              <MenuItem key={type} value={type}>
                <div>
                  <strong>
                    {scanTimingLabels[type].label} ({type})
                  </strong>
                  <br />
                  <small>{scanTimingLabels[type].description}</small>
                </div>
              </MenuItem>
            ))}
          </Select>
        </FormControl>
      )}

      {scanOption !== ScanTypes.SCAN_PING && (
        <TextField
          style={{ marginTop: "22px" }}
          label="Port range"
          variant="outlined"
          value={portRange}
          onChange={(e) => setPortRange(e.target.value)}
          helperText={
            !isValidPortRange(portRange)
              ? "Invalid format. Example: 80,443 or 1-100"
              : "Example: 80,443 or 1-100"
          }
          error={!isValidPortRange(portRange)}
        />
      )}

      <Button
        type="submit"
        variant="contained"
        color="primary"
        disabled={
          !isValidScanId(scanId) ||
          (scanOption !== ScanTypes.SCAN_PING && !isValidPortRange(portRange))
        }
      >
        Start Scan
      </Button>
    </Box>
  );
};

export default ScanFormIp;

const isValidPortRange = (value: string): boolean => {
  if (value.trim() === "") return true;
  const pattern = /^(\d+(-\d+)?)(,(\d+(-\d+)?))*$/;
  return pattern.test(value);
};
