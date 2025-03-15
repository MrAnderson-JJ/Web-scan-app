import React, { useCallback, useState, useEffect } from "react";
import { UserScan } from "@/types";
import { DataGrid, GridColDef } from "@mui/x-data-grid";
import Paper from "@mui/material/Paper";
import Button from "@mui/material/Button";
import { deleteUserScans } from "@/api/userApi";
import { useKeycloak } from "@react-keycloak/web";

interface UserScansTableProps {
  userScans: UserScan[];
}

const paginationModel = { page: 0, pageSize: 10 };

const UserScansTable: React.FC<UserScansTableProps> = ({ userScans }) => {
  const { keycloak } = useKeycloak();
  const [rows, setRows] = useState<UserScan[]>(userScans);

  useEffect(() => {
    setRows(userScans);
  }, [userScans]);

  const handleButtonClick = useCallback(
    async (scanId: string) => {
      if (!keycloak.subject) {
        throw new Error("Keycloak subject is not set");
      }
      try {
        await deleteUserScans(keycloak.subject, [scanId]);
        console.log(`Scan ${scanId} deleted successfully`);

        // remove deleted row instantly
        setRows((prevRows) => prevRows.filter((row) => row.scanId !== scanId));
      } catch (error) {
        console.error("Error deleting scan:", error);
      }
    },
    [keycloak.subject]
  );
  const handleButtonDetail = useCallback(
    (scanId: string) => {
      window.location.href = `/dashboard/${scanId}`;
    },
    [keycloak.subject]
  );

  const columns: GridColDef[] = [
    { field: "scanId", headerName: "ID", width: 250 },
    { field: "scanType", headerName: "Scan type", width: 130 },
    {
      field: "action",
      headerName: "Delete",
      width: 150,
      renderCell: (params) => (
        <Button
          variant="contained"
          color="primary"
          onClick={() => handleButtonClick(params.row.scanId)}
        >
          Delete
        </Button>
      ),
    },
    {
      field: "detail",
      headerName: "Detail",
      width: 150,
      renderCell: (params) => (
        <Button
          variant="contained"
          color="primary"
          onClick={() => handleButtonDetail(params.row.scanId)}
        >
          Detail
        </Button>
      ),
    },
  ];

  return (
    <Paper sx={{ height: "100%", width: "100%" }}>
      <DataGrid
        getRowId={(row) => row.scanId}
        rows={rows}
        columns={columns}
        initialState={{ pagination: { paginationModel } }}
        pageSizeOptions={[5, 10]}
        checkboxSelection
        disableRowSelectionOnClick={true}
        sx={{ border: 0 }}
      />
    </Paper>
  );
};

export default UserScansTable;
