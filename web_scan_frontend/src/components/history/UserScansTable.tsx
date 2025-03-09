import React from "react";
import { UserScan } from "@/types";
import { DataGrid, GridColDef } from "@mui/x-data-grid";
import Paper from "@mui/material/Paper";

const columns: GridColDef[] = [
  { field: "scanId", headerName: "ID", width: 250 },
  { field: "scanType", headerName: "Scan type", width: 130 },
];

interface UserScansTableProps {
  userScans: UserScan[];
}

const paginationModel = { page: 0, pageSize: 10 };

const UserScansTable: React.FC<UserScansTableProps> = ({ userScans }) => {
  return (
    <Paper sx={{ height: "100%", width: "100%" }}>
      <DataGrid
        getRowId={(row) => row.scanId}
        rows={userScans}
        columns={columns}
        initialState={{ pagination: { paginationModel } }}
        pageSizeOptions={[5,10]}
        checkboxSelection
        sx={{ border: 0 }}
      />
    </Paper>
  );
};

export default UserScansTable;
