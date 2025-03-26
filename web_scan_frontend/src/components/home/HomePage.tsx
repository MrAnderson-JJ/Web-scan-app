import React, { useState } from "react";
import ScanFormIp from "../scanForm/scanFormIp";
import ScanFormTable from "./ScanFormTableComponent";
import { fetchTest } from "../../api/portApi";
import { Card, Typography } from "@mui/material";

const HomePage = () => {
  fetchTest("test");
  console.log(fetchTest("test"));
  const [webSocketId, setScanMessage] = useState("");
  const [loading, setLoading] = useState(false);

  const handleScanResult = (result: string) => {
    setLoading(false);
    setScanMessage(result);
    console.log("KOKOKOOKOKOKOT", result);
  };

  return (
    <>
      <div style={{ padding: "40px", maxWidth: "1600px", margin: "0 auto" }}>
        <Typography variant="h4" gutterBottom>
          Síťový skener
        </Typography>
        <Typography variant="body1" gutterBottom>
          Zadejte IP adresu nebo rozsah pro spuštění nového skenování sítě.
        </Typography>
        <Card style={{ padding: "20px", marginBottom: "20px" }}>
          <Typography variant="h5" style={{ paddingBottom: "5px" }}>
            Nové skenování sítě
          </Typography>
          <ScanFormIp onSubmit={handleScanResult} />
        </Card>
        {webSocketId && <ScanFormTable key={webSocketId} webSocketId={webSocketId} />}{" "} {/** when the key changes it remounts whole component */}
        {/* Shows table only when scanId is set */}
      </div>
    </>
  );
};

export default HomePage;
