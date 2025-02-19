import { useState, useEffect } from "react";
import { Routes, Route, BrowserRouter } from "react-router-dom";
import Layout from "./components/Layout";
import { Home } from "./components/home/Home";
import { Host, Port } from "./types";
import { fetchPortsByScanId } from "./api/portApi";
import { fetchQuick } from "./api/portApi";
import  HistoryPage  from "./components/history/HistoryPage";
import AllRoutesComponent from "./routes/AllRoutesComponent";

export default function App() {
  const [ports, setPorts] = useState<Host[]>([]); // ✅ Always an array
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const loadPorts = async () => {
      setLoading(true);
      setError(null);

      try {
        const data = await fetchQuick("67992e665462ef153926fc44");
        setPorts(data);
        console.log("dasd");
        console.log(ports);
      } catch (err) {
        setError("Failed to load ports. Try again later.");
      } finally {
        setLoading(false);
      }
    };

    loadPorts();
  }, []); // ✅ Empty dependency array ensures it runs only once

  return (
    <div className="App">
      <AllRoutesComponent />
    </div>
  );
}
