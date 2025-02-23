import { Port, Address, HostStatus, Trace } from "./index";

export interface Host {
    hostStatusDto: HostStatus;
    address: Address;
    ports: Port[];
    trace?: Trace;
}