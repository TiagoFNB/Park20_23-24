import { PriceTablePeriodDTO } from "./PriceTablePeriod.dto";

export interface PriceTableDTO {
    id ?: string;
    parkId: string;
    effectiveTime: string;
    periods: PriceTablePeriodDTO[];
}
