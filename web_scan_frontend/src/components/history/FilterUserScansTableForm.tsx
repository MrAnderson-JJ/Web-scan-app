import { useState, ChangeEvent, FormEvent } from "react";
import {
  TextField,
  Button,
  Grid,
  MenuItem,
  Paper,
  FormControlLabel,
  Switch,
} from "@mui/material";
import { FilterScansDto } from "@/dtos";

const initialFilters: FilterScansDto = {
  port: null,
  maxDistance: null,
  maxOpenPorts: null,
  oneHost: false,
};

interface UserScanProps {
  onFilter: (FilterScansDto: FilterScansDto) => void;
}

const UserScanFilterForm = ({ onFilter }: UserScanProps) => {
  const [filters, setFilters] = useState<FilterScansDto>(initialFilters);

  const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFilters((prev) => ({
      ...prev,
      [name]: value === "" ? null : Number(value),
    }));
  };

  const handleSubmit = (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    onFilter(filters);
  };

  const handleReset = () => {
    setFilters(initialFilters);
    onFilter(initialFilters);
  };

  return (
    <Paper elevation={3} sx={{ p: 2, mb: 3 }}>
      <form onSubmit={handleSubmit}>
        <Grid container spacing={2} alignItems="center">
          <Grid item xs={12} sm={3}>
            <TextField
              label="Filter by port number"
              name="port"
              type="number"
              value={filters.port ?? ""}
              onChange={handleChange}
              fullWidth
            />
          </Grid>
          <Grid item xs={12} sm={3}>
            <TextField
              label="Max distance from host"
              name="maxDistance"
              type="number"
              value={filters.maxDistance ?? ""}
              onChange={handleChange}
              fullWidth
            />
          </Grid>
          <Grid item xs={12} sm={3}>
            <TextField
              label="Max opened ports in host"
              name="maxOpenPorts"
              type="number"
              value={filters.maxOpenPorts ?? ""}
              onChange={handleChange}
              fullWidth
            />
          </Grid>
          <Grid item xs={12} sm={3}>
            <FormControlLabel
              control={
                <Switch
                  checked={filters.oneHost ?? false}
                  onChange={(e) =>
                    setFilters((prev) => ({
                      ...prev,
                      oneHost: e.target.checked,
                    }))
                  }
                  name="oneHost"
                  color="primary"
                />
              }
              label="Scans with only one host"
            />
          </Grid>
          <Grid item xs={12} sm={6}>
            <Button variant="contained" type="submit" fullWidth>
              Filter
            </Button>
          </Grid>
          <Grid item xs={12} sm={6}>
            <Button variant="outlined" onClick={handleReset} fullWidth>
              Reset
            </Button>
          </Grid>
        </Grid>
      </form>
    </Paper>
  );
};

export default UserScanFilterForm;
