import React from "react";
import { Grid, Card, CardContent, Typography } from "@mui/material";
import OsPieChart from "./OsTable";
import TraceRouteChart from "./TraceRoute";
import { Os } from "../../../types/Os";
import { Trace } from "../../../types/Trace";

interface ChartsDashboardProps {
  osData: Os;
  traceData: Trace;
}

const ChartsDashboard: React.FC<ChartsDashboardProps> = ({ osData, traceData }) => {
  return (
    <Grid container spacing={3} sx={{ padding: 3 }}>
      {/* OS Pie Chart */}
      <Grid item xs={12} md={6}>
        <Card sx={{ boxShadow: 3, borderRadius: 3 }}>
          <CardContent>
            <Typography variant="h6" gutterBottom>
              OS Detection Accuracy
            </Typography>
            <OsPieChart data={osData} />
          </CardContent>
        </Card>
      </Grid>

      {/* Traceroute Chart */}
      <Grid item xs={12} md={6}>
        <Card sx={{ boxShadow: 3, borderRadius: 3 }}>
          <CardContent>
            <Typography variant="h6" gutterBottom>
              Traceroute RTT Graph
            </Typography>
            <TraceRouteChart data={traceData} />
          </CardContent>
        </Card>
      </Grid>
    </Grid>
  );
};

export default ChartsDashboard;
