import { useState, ChangeEvent, FormEvent } from "react";
import { TextField, Button, Grid, MenuItem, Paper } from "@mui/material";
import { FilterScansDto } from "@/types";

export type FilterValues = {
  port: string;
  maxDistance: string;
  os: string;
};

interface UserScanProps {
    onFilter: (FilterScansDto: FilterScansDto) => void;
}

/* type NmapFilterFormProps = {
  onFilter: (filters: FilterValues) => void;
}; */

const osOptions = ["Windows", "Linux", "macOS", "Other"];

const UserScanFilterForm = ({ onFilter }: UserScanProps) => {
const [filters, setFilters] = useState<FilterScansDto>({});

  const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFilters((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleSubmit = (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    onFilter(filters);
  };

  const handleReset = () => {
    const resetValues: FilterScansDto = {};
    setFilters(resetValues);
    onFilter(resetValues);
  };

  return (
    <Paper elevation={3} sx={{ p: 2, mb: 3 }}>
      <form onSubmit={handleSubmit}>
        <Grid container spacing={2} alignItems="center">
          <Grid item xs={12} sm={4}>
            <TextField
              label="Port"
              name="port"
              type="number"
              value={filters.port}
              onChange={handleChange}
              fullWidth
            />
          </Grid>
          <Grid item xs={12} sm={4}>
            <TextField
              label="Max vzdálenost"
              name="maxDistance"
              type="number"
              value={filters.maxDistance}
              onChange={handleChange}
              fullWidth
            />
          </Grid>
          <Grid item xs={12} sm={4}>
            <TextField
              label="Operační systém"
              name="os"
              select
              value="os"
              onChange={handleChange}
              fullWidth
            >
              <MenuItem value="">Vše</MenuItem>
              {osOptions.map((option) => (
                <MenuItem key={option} value={option}>
                  {option}
                </MenuItem>
              ))}
            </TextField>
          </Grid>
          <Grid item xs={12} sm={6}>
            <Button variant="contained" type="submit" fullWidth>
              Filtrovat
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
