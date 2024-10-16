import { VehicleDTO } from "./Vehicle.dto";

export interface CustomerDTO {
  id? : string;
  username : string;
  password: string;
  email: string;
  name : string
  nif: string;
  handicapped: boolean;
  vehicles: VehicleDTO[];
  payments: any;
  language?: string;
  wallet?: {amount: string};
}
