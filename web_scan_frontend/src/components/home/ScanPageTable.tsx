import React, { useEffect, useState } from "react";
import useWebSocket from "../../api/webSocket/useWebSocket";
import { Host } from "../../dtos";
import { fetchIntense } from "../../api/scanApi";
import { ScanTypes } from "@/enums/ScanType";
import ChartsDashboard from "../table/intenseScan/ScanTable";
import { CircularProgress, Typography, Button } from "@mui/material";

interface MessageProps {
  webSocketId: string;
}

const ScanFormTable: React.FC<MessageProps> = ({ webSocketId }) => {
  const { scanResult, isConnected } = useWebSocket(webSocketId);
  const [hosts, setHosts] = useState<Host[]>();
  const [selectedHost, setSelectedHost] = useState<Host | null>(null);
  const [scanFail, setScanFail] = useState<string>();
  const [scanEmpty, setScanEmpty] = useState<string>();

  // Update message when scanResult is received
  useEffect(() => {
    if (scanResult?.scanId === "null") {
      setScanFail("Scanning failed. Check your prompt.");
    }
    if (scanResult) {
      console.log(scanResult.scanResultMessage.scanType);
      const loadHosts = async () => {
        try {
          if (scanResult.scanResultMessage.scanType === ScanTypes.SCAN_PING) {
            const data = await fetchIntense(scanResult.scanId);
            setHosts(data);
            if (data.length === 0) {
              setScanEmpty("Scanning returned no results. Check your prompt.");
            }
            setSelectedHost((data[0] || null));
          } else if (
            scanResult.scanResultMessage.scanType === ScanTypes.SCAN_QUICK
          ) {
            const data = await fetchIntense(scanResult.scanId);
            setHosts(data);
            if (data.length === 0) {
              setScanEmpty("Scanning returned no results. Check your prompt.");
            }
            setSelectedHost((data[0] || null));
          } else if (
            scanResult.scanResultMessage.scanType === ScanTypes.SCAN_FULL
          ) {
            const data = await fetchIntense(scanResult.scanId);
            console.log(data);
            setHosts(data);
            if (data.length === 0) {
              setScanEmpty("Scanning returned no results. Check your prompt.");
            }
            setSelectedHost((data[0] || null));
          }
        } catch (err) {
          console.error("Failed to load hosts.");
        }
      };
      loadHosts();
    }
  }, [scanResult]);

  return (
    <div>
      <Typography variant="h6">
        WS status: {isConnected ? "Connected" : "Disconnected"}
      </Typography>
      {!scanResult && (
        <div style={{ textAlign: "center" }}>
          <Typography
            variant="h5"
            gutterBottom
            style={{ marginBottom: "10px" }}
          >
            Scanning host..
          </Typography>
          <CircularProgress size={60} />
        </div>
      )}
      <pre style={{ borderRadius: "0px" }}>
        <div style={{ padding: "0px" }}>
          {scanFail && (
            <Typography
              style={{ color: "red", fontSize: "1.5rem", textAlign: "center" }}
            >
              {scanFail}
            </Typography>
          )}
          {scanEmpty && (
            <Typography
              style={{ color: "red", fontSize: "1.5rem", textAlign: "center" }}
            >
              {scanEmpty}
            </Typography>
          )}
        </div>
        {!scanFail && hosts && (
          <div>
            <div style={{ display: "flex", gap: "8px", flexWrap: "wrap" }}>
              {hosts.map((host, index) => (
                <Button
                  key={index}
                  variant={selectedHost === host ? "contained" : "outlined"}
                  onClick={() => setSelectedHost(host)}
                >
                  {`Host ${host.address.addr}`}
                </Button>
              ))}
            </div>
            {selectedHost && <ChartsDashboard host={selectedHost} />}
          </div>
        )}
      </pre>
    </div>
  );
};

export default ScanFormTable;
