import React, { useEffect, useState } from "react";
import useWebSocket from "../../api/webSocket/useWebSocket";
import { Host } from "../../types";
import { fetchPing, fetchQuick, fetchIntense } from "../../api/portApi";
import PingTable from "../table/PingTable";
import HostTable from "../table/HostTable";
import { Ping } from "@/types/Ping";
import { ScanTypes } from "@/types/ScanType";
import ChartsDashboard from "../table/intenseScan/ScanTable";

interface MessageProps {
  webSocketId: string;
}

const ScanFormTable: React.FC<MessageProps> = ({ webSocketId }) => {
  const { scanResult, isConnected } = useWebSocket(webSocketId);
  const [message, setMessage] = useState<string>("Čekání na výsledek skenu...");
  const [pings, setPing] = useState<Ping>();
  const [hosts, setHosts] = useState<Host[]>();
  const [intenseScan, setIntenseScan] = useState<Host[]>();

  // Update message when scanResult is received
  useEffect(() => {
    if (scanResult) {
      console.log(scanResult.scanResultMessage.scanType);
      const loadHosts = async () => {
        try {
          if (scanResult.scanResultMessage.scanType === ScanTypes.SCAN_PING) {
            const data = await fetchPing(scanResult.scanId);
            setPing(data);
          } else if (scanResult.scanResultMessage.scanType === ScanTypes.SCAN_QUICK) {
            const data = await fetchIntense(scanResult.scanId);
            setHosts(data);
          } else if (scanResult.scanResultMessage.scanType === ScanTypes.SCAN_FULL) {
            const data = await fetchIntense(scanResult.scanId);
            console.log(data);
            setIntenseScan(data);
          }
        } catch (err) {
          console.error("Failed to load hosts.");
        }
      };
      loadHosts();
      setMessage(JSON.stringify(scanResult.scanResultMessage.jsonData, null, 2)); // Format JSON for display
      console.log(scanResult);
    }
  }, [scanResult]);

  return (
    <div style={{ padding: "0px" }}>
      <h2>Status WebSocketu: {isConnected ? "🔵 Připojeno" : "🔴 Odpojeno"}</h2>
      <h1>Výsledek skenu:</h1>
      <pre
        style={{borderRadius: "0px" }}
      >
        <div style={{ padding: "0px" }}>
          <h1>Host Table</h1>
            {scanResult?.scanResultMessage.scanType === ScanTypes.SCAN_PING && pings && <PingTable ping={pings} />}
          {scanResult?.scanResultMessage.scanType === ScanTypes.SCAN_QUICK && hosts && <ChartsDashboard host={hosts[0]} />}
          {scanResult?.scanResultMessage.scanType === ScanTypes.SCAN_FULL && intenseScan && intenseScan[0]?.trace && intenseScan[0]?.os && <ChartsDashboard host={intenseScan[0]} />}
        </div>
      </pre>
    </div>
  );
};

export default ScanFormTable;
