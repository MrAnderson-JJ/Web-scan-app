import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { Host, NmapRunDto } from "../../types";
import { fetchIntense, fetchLastScan } from "../../api/scanApi";
import ChartsDashboard from "../table/intenseScan/ScanTable";
import { Typography, Button } from "@mui/material";

const ScanDashboard: React.FC = () => {
  const { scanId } = useParams();
  const [intenseScan, setIntenseScan] = useState<Host[]>();
  const [lastScan, setLastScan] = useState<NmapRunDto>();
  const [selectedHost, setSelectedHost] = useState<Host | null>(null);
  const [isLatest, setIsLatest] = useState(false);

  useEffect(() => {
    const loadHosts = async () => {
      if (scanId) {
        try {
          const data = await fetchIntense(scanId);
          setIntenseScan(data);
          setSelectedHost(data[0] || null);
          setIsLatest(false);
        } catch (err) {
          console.error("Failed to load hosts.", err);
        }
      } else {
        try {
          const data = await fetchLastScan();
          setLastScan(data);
          setSelectedHost(data.hosts[0] || null);
          setIsLatest(true);
        } catch (err) {
          console.error("Failed to load hosts.", err);
        }
      }
    };

    loadHosts();
  }, [scanId]);

  const renderHeader = () => (
    <div style={{ marginBottom: "20px" }}>
      <h1 style={{ fontSize: "2rem", color: "#2c3e50", marginBottom: "10px" }}>
        Dashboard skenování
      </h1>
      <p style={{ fontSize: "1rem", color: "#555" }}>
        Zde najdeš výsledky{" "}
        {isLatest ? "posledního skenu uživatele" : "konkrétního skenu"}.
      </p>
    </div>
  );

  return (
    <div style={{ padding: "20px" }}>
      {renderHeader()}

      <h1 style={{ marginBottom: "10px" }}>
        {isLatest && (
          <span
            style={{
              backgroundColor: "#e0f7fa",
              color: "#00796b",
              fontSize: "0.6em",
              padding: "4px 8px",
              borderRadius: "12px",
              marginLeft: "10px",
              verticalAlign: "middle",
            }}
          >
            Poslední sken uživatele
          </span>
        )}
      </h1>

      <div>
        {intenseScan && (
          <div>
            <div style={{ display: "flex", gap: "8px", flexWrap: "wrap" }}>
              {intenseScan.map((host, index) => (
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
        {lastScan && (
          <div>
            <div style={{ display: "flex", gap: "8px", flexWrap: "wrap" }}>
              {lastScan.hosts.map((host, index) => (
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
        {!intenseScan && !lastScan && <h2>Žádný sken nebyl nalezen.</h2>}
      </div>
    </div>
  );
};

export default ScanDashboard;
