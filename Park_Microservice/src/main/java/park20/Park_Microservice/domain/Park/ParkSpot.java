package park20.Park_Microservice.domain.Park;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.apache.commons.lang3.Validate;
import park20.Park_Microservice.domain.Vehicle.VehicleCategory;
import park20.Park_Microservice.shared.ValueObject;

import javax.persistence.*;


@javax.persistence.Entity
@Table(name = "ParkSpot")
public class ParkSpot implements ValueObject<ParkSpot> {


    @EmbeddedId
    @JsonUnwrapped
    private ParkSpotInternalId id;


    @JsonUnwrapped
    @Column(nullable = false)
    private boolean isOcupied;

    @Embedded
    @JsonUnwrapped
    private VehicleCategory type;


    @JsonUnwrapped
    @Column(nullable = false)
    private Integer floor;


    protected ParkSpot() {
    }

    public ParkSpot(ParkSpotInternalId id, boolean isOcupied, VehicleCategory type, Integer floor) {
        this.id = id;
        this.isOcupied = isOcupied;
        this.type = type;
        this.floor = floor;
    }

    public ParkSpotInternalId getId() {
        return id;
    }


    public boolean isOcupied() {
        return isOcupied;
    }

    public VehicleCategory getType() {
        return type;
    }

    public Integer getFloor() {
        return floor;

    }
    
    public void reserveSlot(){
        this.isOcupied=true;
    }

    public void openSlot(){
        this.isOcupied=false;
    }

    @Override
    public boolean sameValueAs(ParkSpot other) {
        return false;
    }
}
