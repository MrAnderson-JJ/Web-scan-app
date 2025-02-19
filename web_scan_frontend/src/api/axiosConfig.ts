import axios, { AxiosInstance } from 'axios';

const axiosInstance: AxiosInstance = axios.create({
    baseURL: 'http://localhost:8081/api/',
    headers: {"ngrok-skip-browser-warning": "true"}
});

const axiosInstanceScan: AxiosInstance = axios.create({
    baseURL: 'http://localhost:8080/api/scan/',
    headers: {"ngrok-skip-browser-warning": "true"}
});

export { axiosInstance, axiosInstanceScan };