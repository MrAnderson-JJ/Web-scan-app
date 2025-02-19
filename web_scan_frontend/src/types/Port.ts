export interface Port {
    id: string;
    hostRefId: string; // Reference to Host
    portId: string;
    protocol: string;
    state: string;
    service: string;
}