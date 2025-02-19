import React, { useEffect, useState } from "react";
import HostTable from "../table/HostTable";
import { Host } from "../../types";
import { fetchQuick } from "../../api/portApi";

interface hostProps {
  scanId: string;
}

const HomeHostTable = ({ scanId }: hostProps) => {
  const [hosts, setHosts] = useState<Host[]>([]);

  useEffect(() => {
    const loadHosts = async () => {
        try {
            const data = await fetchQuick(scanId);
            setHosts(data);
        } catch (err) {
            console.error("Failed to load hosts.");
        }
    }
    loadHosts();
  }, [scanId]);

  return (
    <div style={{ padding: "20px" }}>
      <h1>Host Table</h1>
      <HostTable hosts={hosts} />
    </div>
  );
};

export default HomeHostTable;
