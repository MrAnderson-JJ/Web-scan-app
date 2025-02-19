import { Port, Address, HostStatus } from "./index";

export interface Host {
    hostStatusDto: HostStatus;
    address: Address;
    ports: Port[];
}