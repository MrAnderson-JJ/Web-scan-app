import React, { useState } from 'react';
import { Container, TextField, Button, MenuItem, Select, FormControl, InputLabel, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper } from '@mui/material';

const ScanDashboard: React.FC = () => {
    const [ip, setIp] = useState('');
    const [scanType, setScanType] = useState('');
    const [completedScans, setCompletedScans] = useState<Array<{ ip: string, scanType: string, result: string }>>([]);

    const handleScan = () => {
        // Simulate a scan result
        const result = `Result for ${scanType} scan on IP ${ip}`;
        setCompletedScans([...completedScans, { ip, scanType, result }]);
    };

    return (
        <Container>
            <form noValidate autoComplete="off">
                <TextField
                    label="IP Address"
                    value={ip}
                    onChange={(e) => setIp(e.target.value)}
                    fullWidth
                    margin="normal"
                />
                <FormControl fullWidth margin="normal">
                    <InputLabel>Scan Type</InputLabel>
                    <Select
                        value={scanType}
                        onChange={(e) => setScanType(e.target.value as string)}
                    >
                        <MenuItem value="Ping">Ping</MenuItem>
                        <MenuItem value="Port">Port</MenuItem>
                        <MenuItem value="Traceroute">Traceroute</MenuItem>
                    </Select>
                </FormControl>
                <Button variant="contained" color="primary" onClick={handleScan}>
                    Start Scan
                </Button>
            </form>
            <TableContainer component={Paper} style={{ marginTop: '20px' }}>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell>IP Address</TableCell>
                            <TableCell>Scan Type</TableCell>
                            <TableCell>Result</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {completedScans.map((scan, index) => (
                            <TableRow key={index}>
                                <TableCell>{scan.ip}</TableCell>
                                <TableCell>{scan.scanType}</TableCell>
                                <TableCell>{scan.result}</TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </Container>
    );
};

export default ScanDashboard;