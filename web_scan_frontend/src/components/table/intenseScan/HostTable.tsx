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
  TextField,
  Select,
  MenuItem,
  InputAdornment,
} from "@mui/material";
import { KeyboardArrowDown, KeyboardArrowUp, Search } from "@mui/icons-material";
import { Host } from "../../../types/Host";
import OpenPortsTable from "./OpenPortsTable";

interface HostTableProps {
  host: Host;
}

const HostTable: React.FC<HostTableProps> = ({ host }) => {
  const [openRows, setOpenRows] = useState< boolean>(false);
  const [searchTerm, setSearchTerm] = useState<string>("");
  const [statusFilter, setStatusFilter] = useState<string>("All");

  return (
    <Box sx={{ padding: 3 }}>
      {/* Filters */}
      <Box sx={{ display: "flex", justifyContent: "space-between", mb: 2 }}>
        {/* Search by IP Address */}
        <TextField
          variant="outlined"
          placeholder="Search IP Address"
          size="small"
          sx={{ width: "40%" }}
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
          InputProps={{
            startAdornment: (
              <InputAdornment position="start">
                <Search />
              </InputAdornment>
            ),
          }}
        />

        {/* Filter by Status */}
        <Select
          value={statusFilter}
          onChange={(e) => setStatusFilter(e.target.value)}
          displayEmpty
          size="small"
          sx={{ width: "20%" }}
        >
          <MenuItem value="All">All Statuses</MenuItem>
          <MenuItem value="up">Up</MenuItem>
          <MenuItem value="down">Down</MenuItem>
        </Select>
      </Box>

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
                      <IconButton size="small" onClick={() => setOpenRows(!openRows)}>
                        {openRows ? <KeyboardArrowUp /> : <KeyboardArrowDown />}
                      </IconButton>
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
