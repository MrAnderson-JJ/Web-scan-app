import React, { useEffect, useState } from "react";
import useWebSocket from "../../api/webSocket/useWebSocket";
import { Host } from "../../types";
import { fetchPing, fetchQuick, fetchIntense } from "../../api/portApi";
import PingTable from "../table/PingTable";
import HostTable from "../table/HostTable";
import { Ping } from "@/types/Ping";
import { ScanTypes } from "@/types/ScanType";
import ChartsDashboard from "../table/intenseScan/ScanTable";
import { Container, TextField, Button, MenuItem, Select, FormControl, InputLabel, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper } from '@mui/material';

const ScanDashboard: React.FC = () => {
    const [pings, setPing] = useState<Ping>();
    const [hosts, setHosts] = useState<Host[]>();
    const [intenseScan, setIntenseScan] = useState<Host[]>();
  
    // Update message when scanResult is received
    useEffect(() => {
        const loadHosts = async () => {
          try {
              const scanId = window.location.pathname.split('/').pop();
              if (scanId) {
                const data = await fetchIntense(scanId);
                console.log(data);
                setIntenseScan(data);
              } else {
                console.error("scanId not found in URL.");
              }
          } catch (err) {
            console.error("Failed to load hosts.");
          }
        };
        loadHosts();
    }, []);

    return (
        <div style={{ padding: "0px" }}>
          <h1>Výsledek skenu:</h1>
          <pre
            style={{borderRadius: "0px" }}
          >
            <div style={{ padding: "0px" }}>
              <h1>Host Table</h1>
              { intenseScan && intenseScan[0]?.trace && intenseScan[0]?.os && <ChartsDashboard host={intenseScan[0]} />}
            </div>
          </pre>
        </div>
      );
};

export default ScanDashboard;