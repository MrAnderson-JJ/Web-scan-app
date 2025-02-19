import { useEffect, useState } from "react";
import SockJS from "sockjs-client";
import { Client } from "@stomp/stompjs";
import { WebSocketMessage } from "./model/webSocetMessage";

const WEBSOCKET_URL = "http://localhost:8081/ws"; // URL backendu

const useWebSocket = (webSocketId: string | null) => {
  const [scanResult, setScanResult] = useState<WebSocketMessage | null>(null);
  const [isConnected, setIsConnected] = useState(false);

  useEffect(() => {
    if (!webSocketId) return;

    const socket = new SockJS(WEBSOCKET_URL); // Vytvoření SockJS klienta
    const stompClient = new Client({
      webSocketFactory: () => socket,
      reconnectDelay: 5000, // Automatické připojení po odpojení
      debug: (msg) => console.log("WebSocket Debug:", msg),
    });

    stompClient.onConnect = () => {
      console.log("✅ WebSocket připojen");
      setIsConnected(true);

      // Přihlášení k odběru zpráv po úspěšném připojení
      const subscription = stompClient.subscribe(
        `/topic/scanResults/${webSocketId}`,
        (message) => {
          console.log("📡 Received message:", message.body);

          try {
            const parsedMessage: WebSocketMessage = JSON.parse(message.body);
            setScanResult(parsedMessage);
          } catch (error) {
            console.error("🚨 Error parsing WebSocket message:", error);
          }
        }
      );

      console.log(`📡 Přihlášen k odběru: /topic/scanResults/${webSocketId}`);

      // Odpojení při unmountu
      return () => {
        subscription.unsubscribe();
        stompClient.deactivate();
      };
    };

    stompClient.onDisconnect = () => {
      console.log("❌ WebSocket odpojen");
      setIsConnected(false);
    };

    socket.onclose = (event) => {
      console.error("🚫 WebSocket uzavřen!", event);
      if (event.reason) {
        console.error("Důvod:", event.reason);
      }
    };

    stompClient.activate(); // Aktivace WebSocket připojení

    return () => {
      stompClient.deactivate(); // Odpojení při unmountu
    };
  }, [webSocketId]);

  return { scanResult, isConnected };
};

export default useWebSocket;
