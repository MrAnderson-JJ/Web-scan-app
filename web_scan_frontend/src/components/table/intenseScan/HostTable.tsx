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
import { Host } from "../../../types/scan/Host";
import OpenPortsTable from "./OpenPortsTable";

interface HostTableProps {
  host: Host;
}

const HostTable: React.FC<HostTableProps> = ({ host }) => {
  const [openRows, setOpenRows] = useState< boolean>(false);

  return (
    <Box sx={{ padding: 3 }}>
      {/* Table */}
      <TableContainer component={Paper} sx={{ borderRadius: 3, boxShadow: 3 }}>
        <Table stickyHeader>
          {/* Table Head */}
          <TableHead>
            <TableRow sx={{ backgroundColor: "#f5f5f5" }}>
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
            {host ? (
                <React.Fragment>
                  {/* Host Row */}
                  <TableRow
                    sx={{
                      backgroundColor: openRows ? "#e3f2fd" : "inherit",
                    }}
                  >
                    <TableCell>
                        {host.ports && host.ports.length > 0 && (
                        <IconButton size="small" onClick={() => setOpenRows(!openRows)}>
                          {openRows ? <KeyboardArrowUp /> : <KeyboardArrowDown />}
                        </IconButton>
                        )}
                    </TableCell>
                    <TableCell>{host.address.addr}</TableCell>
                    <TableCell>{host.address.addrType}</TableCell>
                    <TableCell>
                      <Typography
                        sx={{
                          color: host.hostStatusDto.state === "up" ? "green" : "red",
                          fontWeight: "bold",
                        }}
                      >
                        {host.hostStatusDto.state}
                      </Typography>
                    </TableCell>
                    <TableCell>{host.hostStatusDto.reason}</TableCell>
                  </TableRow>

                  {/* Collapsible Ports Table */}
                  <TableRow>
                    <TableCell style={{ paddingBottom: 0, paddingTop: 0 }} colSpan={5}>
                      <Collapse in={openRows} timeout="auto" unmountOnExit>
                        <OpenPortsTable ports={host.ports} />
                      </Collapse>
                    </TableCell>
                  </TableRow>
                </React.Fragment>
            ) : (
              <TableRow>
                <TableCell colSpan={5} align="center">
                  No host found.
                </TableCell>
              </TableRow>
            )}
          </TableBody>
        </Table>
      </TableContainer>
    </Box>
  );
};

export default HostTable;
