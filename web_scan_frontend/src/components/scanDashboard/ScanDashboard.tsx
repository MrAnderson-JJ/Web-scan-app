import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom"; // Use React Router params
import { Host } from "../../types";
import { fetchIntense } from "../../api/scanApi";
import ChartsDashboard from "../table/intenseScan/ScanTable";

const ScanDashboard: React.FC = () => {
  const { scanId } = useParams(); // Grab scanId from route
  const [intenseScan, setIntenseScan] = useState<Host[]>();

  useEffect(() => {
    const loadHosts = async () => {
      if (!scanId) {
        console.error("No scan ID provided in route.");
        return;
      }

      try {
        const data = await fetchIntense(scanId);
        console.log(data);
        setIntenseScan(data);
      } catch (err) {
        console.error("Failed to load hosts.", err);
      }
    };

    loadHosts();
  }, [scanId]); // scanId is now a dependency

  return (
    <div style={{ padding: "0px" }}>
      <h1>Výsledek skenu:</h1>
      <div style={{ padding: "0px" }}>
        <h1>Host Table</h1>
        {intenseScan && intenseScan[0]?.trace && intenseScan[0]?.os && (
          <ChartsDashboard host={intenseScan[0]} />
        )}
      </div>
    </div>
  );
};

export default ScanDashboard;
