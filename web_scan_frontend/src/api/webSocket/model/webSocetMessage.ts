import { ScanResultMessage } from './scanResultMessage';

export interface WebSocketMessage {
    scanId: string;
    scanResultMessage: ScanResultMessage;
}