import React from "react";
import { Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper } from "@mui/material";
import { Port } from "../../types";

interface PortTableProps {
  ports: Port[];
}

const PortTable = ({ ports }: PortTableProps) => {
  return (
    <TableContainer component={Paper} style={{ maxWidth: "80%", margin: "20px auto" }}>
      <Table>
        <TableHead>
          <TableRow>
            <TableCell><strong>Port ID</strong></TableCell>
            <TableCell><strong>Protocol</strong></TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {ports.length > 0 ? (
            ports.map((port) => (
              <TableRow key={port.id}>
                <TableCell>{port.portId}</TableCell>
                <TableCell>{port.protocol}</TableCell>
              </TableRow>
            ))
          ) : (
            <TableRow>
              <TableCell colSpan={4} align="center">
                No ports found.
              </TableCell>
            </TableRow>
          )}
        </TableBody>
      </Table>
    </TableContainer>
  );
};

export default PortTable;