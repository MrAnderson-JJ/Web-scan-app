import React from "react";
import { PieChart, Pie, Cell, Tooltip, ResponsiveContainer } from "recharts";
import { Os } from "../../../types/scan/Os";

interface OsPieChartProps {
  data: Os;
}

const COLORS = ["#0088FE", "#00C49F", "#FFBB28", "#FF8042", "#8884d8"];

const OsPieChart: React.FC<OsPieChartProps> = ({ data }) => {
  const chartData = data.osMatch.map((osMatch, index) => ({
    name: osMatch.name,
    accuracy: parseInt(osMatch.accuracy)
  }));

  return (
    <div>
      <h2>OS Detection Accuracy</h2>
      <ResponsiveContainer width="100%" height={300}>
        <PieChart>
          <Pie data={chartData} dataKey="accuracy" nameKey="name" cx="50%" cy="50%" outerRadius={100} fill="#8884d8" label>
            {chartData.map((_, index) => (
              <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
            ))}
          </Pie>
          <Tooltip />
        </PieChart>
      </ResponsiveContainer>
    </div>
  );
};

export default OsPieChart;