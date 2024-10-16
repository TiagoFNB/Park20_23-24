import { VehicleDTO } from "./Vehicle.dto"

export interface StayDTO {
  stayId : string
  stayStartTime : string
  stayEndTime : string
  parkId : string
  vehicle : VehicleDTO
}

