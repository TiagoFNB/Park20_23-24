package park20.Park_Microservice.dto;

import park20.Park_Microservice.domain.Vehicle.VehicleCategory;

import java.io.Serializable;

public class ParkSpotDTO implements Serializable {

    public String parkSpotId;

    public boolean isOccupied;

    public VehicleCategory category;

    public Integer floor;

}
