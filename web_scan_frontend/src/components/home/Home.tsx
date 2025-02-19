import { useState, useEffect } from "react";
import { fetchPortsByScanId } from "../../api/portApi";
import { Port } from "../../types";
import PortTable from "../../components/table/PortTable";

interface HomeProps {
    scanId: string;
  }
  
  export const Home = ({ scanId }: HomeProps) => {
    const [ports, setPorts] = useState<Port[]>([]);
    const [loading, setLoading] = useState<boolean>(true);
    const [error, setError] = useState<string | null>(null);
  
    useEffect(() => {
      const loadPorts = async () => {
        setLoading(true);
        setError(null);
        try {
          const data = await fetchPortsByScanId(scanId);
          setPorts(data);
        } catch (err) {
          setError("Failed to load ports.");
        } finally {
          setLoading(false);
        }
      };
  
      loadPorts();
    }, [scanId]);
  
    if (loading) return <h2>Loading ports...</h2>;
    if (error) return <h2 style={{ color: "red" }}>{error}</h2>;
  
    return <PortTable ports={ports} />;
  };