package park20.Park_Microservice.dto;

import java.io.Serializable;

public class PriceTablePeriodDTO implements Serializable {

    public String id;
    public String startTime;
    public String endTime;
    public PriceTableFractionDTO[] fractions;


}
