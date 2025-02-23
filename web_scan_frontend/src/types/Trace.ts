import { Hop } from "./index";

export interface Trace {
    port: String;
    proto: String;
    hop: Hop[];
}