import { PriceTableFractionDTO } from "./PriceTableFraction.dto";

export interface PriceTablePeriodDTO {
    id ?: string;
    startTime: string;
    endTime: string;
    fractions: PriceTableFractionDTO[];
}
