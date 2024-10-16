package park20.Park_Microservice.dto;

import java.io.Serializable;
import java.util.List;

public class GetParksManagerDTO implements Serializable {

    public List<String> parkIds;

    public String error;

}
