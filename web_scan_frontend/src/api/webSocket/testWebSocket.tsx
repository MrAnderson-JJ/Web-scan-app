import { useEffect, useState } from "react";
import SockJS from "sockjs-client";
import { Client } from "@stomp/stompjs";

const WEBSOCKET_URL = "http://localhost:8081/ws"; // ✅ Adjust this if needed

interface WebSocketListenerProps {
  scanId: string | null;
}

const WebSocketListener: React.FC<WebSocketListenerProps> = ({ scanId }) => {
  const [messages, setMessages] = useState<string[]>([]);
  const [isConnected, setIsConnected] = useState(false);

  useEffect(() => {
    // ✅ Create WebSocket connection
    const socket = new SockJS(WEBSOCKET_URL);
    const stompClient = new Client({
      webSocketFactory: () => socket,
      debug: (msg) => console.log("WebSocket Debug:", msg),
      reconnectDelay: 5000, // Auto-reconnect after 5s
    });

    stompClient.onConnect = () => {
      console.log("✅ WebSocket Connected!");
      setIsConnected(true);

      // ✅ Subscribe to the topic "/topic/scanResults/"
      console.log(`📡 Subscribed to: /topic/scanResults/${scanId}`);
      stompClient.subscribe(`/topic/scanResults/${scanId}`, (message) => {
        console.log("📡 Received message:", message.body);
        setMessages((prevMessages) => [...prevMessages, message.body]);
      });
    };

    stompClient.onDisconnect = () => {
      console.log("❌ WebSocket Disconnected!");
      setIsConnected(false);
    };

    stompClient.activate(); // Start WebSocket connection

    return () => {
      stompClient.deactivate(); // Cleanup on component unmount
    };
  }, [scanId]);

  return (
    <div style={{ padding: "20px" }}>
      <h2>Status WebSocketu: {isConnected ? "🟢 Připojeno" : "🔴 Odpojeno"}</h2>
      <h3>Přijaté zprávy:</h3>
      <ul>
        {messages.map((msg, index) => (
          <li key={index} style={{ background: "#f4f4f4", padding: "10px", margin: "5px 0", borderRadius: "5px" }}>
            {msg}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default WebSocketListener;