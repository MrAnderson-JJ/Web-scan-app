import React, { useState } from "react";
import {
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Paper,
  IconButton,
  Collapse,
  Box,
  Typography,
} from "@mui/material";
import { KeyboardArrowDown, KeyboardArrowUp } from "@mui/icons-material";
import { Host } from "../../types";

interface HostTableProps {
  hosts: Host[];
}

const HostTable: React.FC<HostTableProps> = ({ hosts }) => {
  const [openRows, setOpenRows] = useState<{ [key: number]: boolean }>({});

  const toggleRow = (index: number) => {
    setOpenRows((prev) => ({ ...prev, [index]: !prev[index] }));
  };
  console.log(hosts);
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
              <strong>Status</strong>
            </TableCell>
            <TableCell>
              <strong>Reason</strong>
            </TableCell>
          </TableRow>
        </TableHead>

        {/* Table Body */}
        <TableBody>
          {hosts.map((host, index) => (
            <React.Fragment key={index}>
              {/* Host Row */}
              <TableRow>
                <TableCell>
                  <IconButton size="small" onClick={() => toggleRow(index)}>
                    {openRows[index] ? (
                      <KeyboardArrowUp />
                    ) : (
                      <KeyboardArrowDown />
                    )}
                  </IconButton>
                </TableCell>
                <TableCell>{host.address.addr}</TableCell>
                <TableCell>{host.address.addrType}</TableCell>
                <TableCell>{host.hostStatusDto.state}</TableCell>
                <TableCell>{host.hostStatusDto.reason}</TableCell>
              </TableRow>

              {/* Collapsible Ports Table */}
              <TableRow>
                <TableCell
                  style={{ paddingBottom: 0, paddingTop: 0 }}
                  colSpan={5}
                >
                  <Collapse in={openRows[index]} timeout="auto" unmountOnExit>
                    <Box margin={2}>
                      <Typography variant="h6" gutterBottom>
                        Open Ports
                      </Typography>
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
                          {host.ports.map((port, portIndex) => (
                            <TableRow key={portIndex}>
                              <TableCell>{port.portId}</TableCell>
                              <TableCell>{port.protocol}</TableCell>
                              <TableCell>{port.state}</TableCell>
                              <TableCell>{port.service}</TableCell>
                            </TableRow>
                          ))}
                        </TableBody>
                      </Table>
                    </Box>
                  </Collapse>
                </TableCell>
              </TableRow>
            </React.Fragment>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
};

export default HostTable;
