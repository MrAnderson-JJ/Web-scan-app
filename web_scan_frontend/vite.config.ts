import path from "path"
import react from "@vitejs/plugin-react"
import { defineConfig } from "vite"
 
export default defineConfig({
  plugins: [react()],
  resolve: {
    alias: {
      "@": path.resolve(__dirname, "./src"),
    },
  },
  define: {
    global: 'window'
  },
  server: {
    host: 'localhost',
    port: 5173,
    strictPort: true,
    hmr: {
      protocol: 'wss',
      host: 'localhost',
      port: 8443, // WebSockety běží přes API Gateway
    },
    proxy: {
      '/ws': {
        target: 'wss://localhost:8443', // Proxy WebSocket přes API Gateway
        ws: true,
        changeOrigin: true,
      },
    },
  },
})