import React, { useState } from "react";
import {
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Paper,
} from "@mui/material";
import { Ping } from "@/dtos/scan/Ping";

interface PingTableProps {
  ping: Ping;
}

const PingTable: React.FC<PingTableProps> = ({ ping }) => {

  console.log(ping);
  return (
    <TableContainer component={Paper}>
      <Table>
        {/* Table Head */}
        <TableHead>
          <TableRow>
            <TableCell />
            <TableCell>
              <strong>IP Address</strong>
            </TableCell>
            <TableCell>
              <strong>Address Type</strong>
            </TableCell>
            <TableCell>
              <strong>State</strong>
            </TableCell>
            <TableCell>
              <strong>Time</strong>
            </TableCell>
          </TableRow>
        </TableHead>

        {/* Table Body */}
        <TableBody>
            <React.Fragment>
              {/* Host Row */}
              <TableRow>
                <TableCell>
                </TableCell>
                <TableCell>{ping.host.address.addr}</TableCell>
                <TableCell>{ping.host.address.addrType}</TableCell>
                <TableCell>{ping.host.hostStatusDto.state}</TableCell>
                <TableCell>{ping.elapsed}</TableCell>
              </TableRow>
            </React.Fragment>
        </TableBody>
      </Table>
    </TableContainer>
  );
};

export default PingTable;
