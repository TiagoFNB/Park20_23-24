package park20.Park_Microservice.dto;

import java.io.Serializable;

public class ParkWithSpotsAndParkyDTO implements Serializable {

    public String parkId;

    public String spotsGas;

    public String spotsGPL;

    public String spotsElectric;

    public String spotsHandicapped;

    public String spotsMotorcycle;

    public String parkyCoinGainHourly;

    public String parkyCoinWorthInTime;

    public String parkyCoinUsageMinimumTime;

}
