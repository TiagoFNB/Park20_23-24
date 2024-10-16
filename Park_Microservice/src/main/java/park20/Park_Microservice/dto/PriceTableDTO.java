package park20.Park_Microservice.dto;

import java.io.Serializable;

public class PriceTableDTO implements Serializable {

    public String id;
    public String parkId;
    public String effectiveTime;
    public PriceTablePeriodDTO[] periods;


}
