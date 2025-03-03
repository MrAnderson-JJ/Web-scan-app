import React, { useState } from "react";
import ScanForm from "../scanForm/scanForm";
import ScanFormIp from "../scanForm/scanFormIp";
import HomeHostTable from "./HomeTableComponent";
import ScanFormTable from "./ScanFormTableComponent";
import OsPieChart from "../table/intenseScan/ScanTable";
import { Os } from "../../types/Os";
import { fetchTest } from "../../api/portApi";

const mockData: Os = {
  osMatch: [
    { name: "Linux 5.0 - 5.4", accuracy: "87", line: "68121", osClass: [] },
    { name: "Windows Server 2016", accuracy: "80", line: "68054", osClass: [] },
    { name: "Linux 4.15 - 5.8", accuracy: "85", line: "67250", osClass: [] }
  ]
};

const HomePage = () => {
  const [scanId, setScanId] = useState("");
  fetchTest("test");
  console.log(fetchTest("test"));
  const [webSocketId, setScanMessage] = useState("");

  return (
    <>
      <div style={{ padding: "20px" }}>
        <h1>Scan start</h1>
        <ScanFormIp onSubmit={setScanMessage} />
        {webSocketId && <ScanFormTable webSocketId={webSocketId} />}{" "}
        {/* ✅ Shows table only when scanId is set */}
      </div>

      <div style={{ padding: "20px" }}>
        <h1>Scan Database</h1>
        <ScanForm onSubmit={setScanId} />{" "}
        {/* ✅ Updates scanId when submitted */}
        {scanId && <HomeHostTable scanId={scanId} />}{" "}
        {/* ✅ Shows table only when scanId is set */}
      </div>
    </>
  );
};

export default HomePage;
