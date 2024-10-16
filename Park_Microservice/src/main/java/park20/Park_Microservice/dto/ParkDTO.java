package park20.Park_Microservice.dto;

import java.io.Serializable;

public class ParkDTO implements Serializable {

    public String id;

    public ParkAddressDTO address;

    public ParkSpotDTO[] spots;

}
