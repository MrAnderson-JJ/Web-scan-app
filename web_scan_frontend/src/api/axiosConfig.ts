import axios, { AxiosInstance } from 'axios';
import keycloak from "./../auth/keycloak";

const axiosInstance: AxiosInstance = axios.create({
    baseURL: '/api/',
    headers: {"ngrok-skip-browser-warning": "true"}
});

const axiosInstanceScan: AxiosInstance = axios.create({
    baseURL: '/api/scan/',
    headers: {"ngrok-skip-browser-warning": "true"}
});

const axiosInstanceApiGateway: AxiosInstance = axios.create({
    baseURL: '/api/',
    headers: {"ngrok-skip-browser-warning": "true"}
});

axiosInstanceApiGateway.interceptors.request.use(async (config) => {
    if (!keycloak.authenticated) {
      await keycloak.init({ onLoad: "login-required" });
    }
  
    const token = keycloak.token;
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    
    return config;
  });

export { axiosInstance, axiosInstanceScan, axiosInstanceApiGateway };