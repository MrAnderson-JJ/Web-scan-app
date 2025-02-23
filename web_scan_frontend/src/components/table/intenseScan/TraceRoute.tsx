import React from "react";
import {
  LineChart,
  Line,
  XAxis,
  YAxis,
  Tooltip,
  ResponsiveContainer,
} from "recharts";
import { Trace } from "@/types/Trace";

interface TraceProps {
  data: Trace;
}

const TraceRouteChart: React.FC<TraceProps> = ({ data }) => {
  const chartData = data.hop.map((hop, index) => ({
    hop: index + 1,
    rtt: parseFloat(hop.rtt.replace("ms", "")), // Convert RTT to number
    ip: hop.ip,
  }));

  return (
    <div>
      <h2>Traceroute RTT Graph</h2>
      <ResponsiveContainer width="100%" height={300}>
        <LineChart data={chartData}>
          <XAxis dataKey="hop" label={{ value: "Hop #", position: "insideBottom" }} />
          <YAxis label={{ value: "RTT (ms)", angle: -90, position: "insideLeft" }} />
          <Tooltip formatter={(value, name, props) => `${value} ms`} />
          <Line type="monotone" dataKey="rtt" stroke="#8884d8" />
        </LineChart>
      </ResponsiveContainer>
    </div>
  );
};

export default TraceRouteChart;
