import { useState } from "react";
import ScanFormIp from "./scanFormIp";
import ScanFormTable from "./ScanFormTableComponent";
import { Card, Typography } from "@mui/material";

const HomePage = () => {
  const [webSocketId, setScanMessage] = useState("");

  return (
    <>
      <div style={{ padding: "40px", maxWidth: "1600px", margin: "0 auto" }}>
        <Typography variant="h4" gutterBottom>
          Network scanner
        </Typography>
        <Typography variant="body1" gutterBottom>
          Enter an IP address or range to start a new network scan.
        </Typography>
        <Card style={{ padding: "20px", marginBottom: "20px" }}>
          <Typography variant="h5" style={{ paddingBottom: "5px" }}>
            New network scan
          </Typography>
          <ScanFormIp onSubmit={setScanMessage} />
        </Card>
        {webSocketId && <ScanFormTable key={webSocketId} webSocketId={webSocketId} />}{" "}
      </div>
    </>
  );
};

export default HomePage;
