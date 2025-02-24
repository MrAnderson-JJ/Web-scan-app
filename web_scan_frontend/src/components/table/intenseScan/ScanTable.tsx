import React from "react";
import { Grid, Card, CardContent, Typography, useTheme } from "@mui/material";
import { motion } from "framer-motion";
import OsPieChart from "./OsTable";
import TraceRouteChart from "./TraceRoute";
import HostTable from "./HostTable";
import { Host } from "../../../types/Host";

interface ChartsDashboardProps {
    host: Host;
}

const ChartsDashboard: React.FC<ChartsDashboardProps> = ({ host }) => {
    const theme = useTheme();
  
    return (
      <Grid container spacing={3} sx={{ padding: 3 }}>
        {/* Host Table */}
        <Grid item xs={12}>
          <motion.div whileHover={{ scale: 1.02 }} whileTap={{ scale: 0.98 }}>
            <Card sx={styles.card(theme)}>
              <CardContent>
                <Typography variant="h6" sx={styles.title}>
                  Host Information
                </Typography>
                <HostTable host={host} />
              </CardContent>
            </Card>
          </motion.div>
        </Grid>
  
        {/* OS Pie Chart */}
        <Grid item xs={12} md={6}>
          <motion.div whileHover={{ scale: 1.02 }} whileTap={{ scale: 0.98 }}>
            <Card sx={styles.card(theme)}>
              <CardContent>
                <Typography variant="h6" sx={styles.title}>
                  OS Detection Accuracy
                </Typography>
                {host.os ? <OsPieChart data={host.os} /> : <Typography>No OS Data</Typography>}
              </CardContent>
            </Card>
          </motion.div>
        </Grid>
  
        {/* Traceroute Chart */}
        <Grid item xs={12} md={6}>
          <motion.div whileHover={{ scale: 1.02 }} whileTap={{ scale: 0.98 }}>
            <Card sx={styles.card(theme)}>
              <CardContent>
                <Typography variant="h6" sx={styles.title}>
                  Traceroute RTT Graph
                </Typography>
                {host.trace ? <TraceRouteChart data={host.trace} /> : <Typography>No Trace Data</Typography>}
              </CardContent>
            </Card>
          </motion.div>
        </Grid>
      </Grid>
    );
  };
  
  const styles = {
    card: (theme: any) => ({
      boxShadow: 4,
      borderRadius: "12px",
      backgroundColor: theme.palette.background.paper,
      transition: "0.3s",
      "&:hover": {
        boxShadow: 6,
      },
    }),
    title: {
      fontWeight: "bold",
      marginBottom: "10px",
    },
  };

export default ChartsDashboard;
