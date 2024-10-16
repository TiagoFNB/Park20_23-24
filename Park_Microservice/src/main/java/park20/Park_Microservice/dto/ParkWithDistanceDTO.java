package park20.Park_Microservice.dto;

import java.io.Serializable;

public class ParkWithDistanceDTO implements Serializable {
        public String id;
        public String designation;

        public ParkAddressDTO address;

        public double distance;

        public Integer spotHandicap;

        public Integer spotEletric;

        public Integer spotGPL;

        public Integer spotGas;

        public Integer spotMotorcycle;

}
