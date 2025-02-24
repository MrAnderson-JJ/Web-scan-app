import React from "react";
import {
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Paper,
  Typography,
  Box,
} from "@mui/material";
import { Port } from "../../../types/Port";

interface OpenPortsTableProps {
  ports: Port[];
}

const OpenPortsTable: React.FC<OpenPortsTableProps> = ({ ports }) => {
  return (
    <Box margin={2}>
      <Typography variant="h6" gutterBottom>
        Open Ports
      </Typography>
      <TableContainer component={Paper} sx={{ borderRadius: 2, boxShadow: 1 }}>
        <Table size="small">
          <TableHead>
            <TableRow>
              <TableCell>
                <strong>Port ID</strong>
              </TableCell>
              <TableCell>
                <strong>Protocol</strong>
              </TableCell>
              <TableCell>
                <strong>State</strong>
              </TableCell>
              <TableCell>
                <strong>Service</strong>
              </TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {ports.length > 0 ? (
              ports.map((port, index) => (
                <TableRow key={index}>
                  <TableCell>{port.portId}</TableCell>
                  <TableCell>{port.protocol}</TableCell>
                  <TableCell>{port.state}</TableCell>
                  <TableCell>{port.service}</TableCell>
                </TableRow>
              ))
            ) : (
              <TableRow>
                <TableCell colSpan={4} align="center">
                  No open ports found.
                </TableCell>
              </TableRow>
            )}
          </TableBody>
        </Table>
      </TableContainer>
    </Box>
  );
};

export default OpenPortsTable;
