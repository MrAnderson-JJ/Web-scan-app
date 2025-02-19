import React, { useEffect, useState } from "react";
import useWebSocket from "../../api/webSocket/useWebSocket";
import { Host } from "../../types";
import { fetchPing, fetchQuick } from "../../api/portApi";
import PingTable from "../table/PingTable";
import { Ping } from "@/types/Ping";

interface MessageProps {
  webSocketId: string;
}

const ScanFormTable: React.FC<MessageProps> = ({ webSocketId }) => {
  const { scanResult, isConnected } = useWebSocket(webSocketId);
  const [message, setMessage] = useState<string>("Čekání na výsledek skenu...");
  const [hosts, setHosts] = useState<Ping>();

  // Update message when scanResult is received
  useEffect(() => {
    if (scanResult) {
      const loadHosts = async () => {
        try {
          const data = await fetchPing(scanResult.scanId);
          console.log(data);
          setHosts(data);
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
    <div style={{ padding: "20px" }}>
      <h2>Status WebSocketu: {isConnected ? "🔵 Připojeno" : "🔴 Odpojeno"}</h2>
      <h1>Výsledek skenu:</h1>
      <pre
        style={{ background: "#f4f4f4", padding: "10px", borderRadius: "5px" }}
      >
        <div style={{ padding: "20px" }}>
          <h1>Host Table</h1>
          {hosts && <PingTable ping={hosts} />}{" "}
        </div>
      </pre>
    </div>
  );
};

export default ScanFormTable;
