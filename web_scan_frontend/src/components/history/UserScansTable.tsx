import React, { useCallback, useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { UserScan } from "@/dtos";
import { DataGrid, GridColDef } from "@mui/x-data-grid";
import Paper from "@mui/material/Paper";
import Button from "@mui/material/Button";
import Snackbar from "@mui/material/Snackbar";
import Alert from "@mui/material/Alert";
import { deleteUserScans } from "@/api/userApi";
import { useKeycloak } from "@react-keycloak/web";
import { format } from "date-fns";

interface UserScansTableProps {
  userScans: UserScan[];
}

const paginationModel = { page: 0, pageSize: 25 };

const UserScansTable: React.FC<UserScansTableProps> = ({ userScans }) => {
  const { keycloak } = useKeycloak();
  const navigate = useNavigate(); // navigate to use navigate
  const [rows, setRows] = useState<UserScan[]>(userScans);
  const [snackbarOpen, setSnackbarOpen] = useState(false);
  const [snackbarMessage, setSnackbarMessage] = useState("");

  useEffect(() => {
    setRows(userScans);
    console.log(userScans);
  }, [userScans]);

  const handleButtonClick = useCallback(
    async (scanId: string) => {
      if (!keycloak.subject) {
        throw new Error("Keycloak subject is not set");
      }
      try {
        await deleteUserScans(keycloak.subject, [scanId]);
        console.log(`Scan ${scanId} deleted successfully`);
        setRows((prevRows) => prevRows.filter((row) => row.scanId !== scanId));
        setSnackbarMessage(`Scan ${scanId} was deleted successfully.`);
        setSnackbarOpen(true);
      } catch (error) {
        console.error("Error deleting scan:", error);
        setSnackbarMessage("Failed to delete scan.");
        setSnackbarOpen(true);
      }
    },
    [keycloak.subject]
  );

  const handleButtonDetail = useCallback(
    (scanId: string) => {
      navigate(`/dashboard/${scanId}`); // Navigate
    },
    [navigate]
  );

  const handleSnackbarClose = () => {
    setSnackbarOpen(false);
  };

  const columns: GridColDef[] = [
    { field: "scanId", headerName: "ID", width: 250 },
    { field: "scanIp", headerName: "Ip address", width: 250 },
    { field: "scanType", headerName: "Scan type", width: 130 },
    { field: "elapsedTime", headerName: "Scan duration", width: 130 },
    {
      field: "action",
      headerName: "Delete",
      width: 150,
      renderCell: (params) => (
        <Button
          variant="contained"
          color="error"
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
          color="info"
          onClick={() => handleButtonDetail(params.row.scanId)}
        >
          Detail
        </Button>
      ),
    },
    { 
      field: "dateStart", 
      headerName: "Scan started", 
      width: 160,
      renderCell: (params) => params.row.dateStart ? format(new Date(params.value), "dd.MM.yyyy HH:mm:ss") : "N/A"
    },
    { 
      field: "dateEnd", 
      headerName: "Scan finished", 
      width: 160,
      renderCell: (params) => params.value ? format(new Date(params.value), "dd.MM.yyyy HH:mm:ss") : "N/A"
    },
  ];

  return (
    <>
      <Paper sx={{ height: "100%", width: "100%" }}>
        <DataGrid
          getRowId={(row) => row.scanId}
          rows={rows}
          columns={columns}
          initialState={{ pagination: { paginationModel } }}
          pageSizeOptions={[5, 10, 25, 50]}
          disableRowSelectionOnClick={true}
          sx={{ border: 0 }}
        />
      </Paper>
      <Snackbar
        open={snackbarOpen}
        autoHideDuration={4000}
        onClose={handleSnackbarClose}
        anchorOrigin={{ vertical: "bottom", horizontal: "center" }}
      >
        <Alert onClose={handleSnackbarClose} severity="success" sx={{ width: "100%" }}>
          {snackbarMessage}
        </Alert>
      </Snackbar>
    </>
  );
};

export default UserScansTable;
