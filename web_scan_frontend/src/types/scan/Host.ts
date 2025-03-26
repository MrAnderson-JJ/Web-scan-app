import { Port, Address, HostStatus, Trace, Os } from "../index";

export interface Host {
    hostStatusDto: HostStatus;
    address: Address;
    ports: Port[];
    trace?: Trace;
    os?: Os;
}