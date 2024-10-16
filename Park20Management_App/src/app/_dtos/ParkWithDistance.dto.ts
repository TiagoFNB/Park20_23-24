import { ParkAddressDTO } from "./ParkAddress.dto";

export interface ParkWithDistanceDTO {
    designation: string,
    address : ParkAddressDTO;
    distance : number;
    spotHandicap : number;
    spotEletric : number;
    spotGPL : number;
    spotGas : number;
    spotMotorcycle: number;
  }