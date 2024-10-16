package park20.Customer_Microservice.domain.Stay;


import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.apache.commons.lang3.Validate;
import park20.Customer_Microservice.domain.Vehicle.Vehicle;
import park20.Customer_Microservice.dto.StayDTO;
import park20.Customer_Microservice.shared.Entity;

import javax.persistence.*;

@javax.persistence.Entity
@Table(name = "Stay")
public class Stay implements Entity<Stay> {

    @EmbeddedId
    @JsonUnwrapped
    private StayInternalId id;

    @Embedded
    @JsonUnwrapped
    private StayStartTime stayStartTime;

    @Embedded
    @JsonUnwrapped
    private StayEndTime stayEndTime;

    @Embedded
    @JsonUnwrapped
    private StayParkId stayParkId;

    @Embedded
    @JsonUnwrapped
    private StaySlotId staySlotId;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    protected Stay() {}

    public Stay(final StayInternalId id, final StayStartTime stayStartTime, final StayParkId stayParkId, final Vehicle vehicle, final StaySlotId staySlotId) {
        Validate.notNull(id, "StayInternalId is required");
        Validate.notNull(stayStartTime, "StayStartTime is required");
        Validate.notNull(stayParkId, "StayParkId is required");
        Validate.notNull(staySlotId, "StaySlotId is required");
        Validate.notNull(vehicle, "Vehicle is required");
        this.id = id;
        this.stayParkId = stayParkId;
        this.stayStartTime = stayStartTime;
        this.vehicle = vehicle;
        this.staySlotId = staySlotId;

    }

    public StayInternalId getId() {
        return id;
    }

    public StayStartTime getStayStartTime() {
        return stayStartTime;
    }

    public StayParkId getStayParkId() {
        return stayParkId;
    }

    public StaySlotId getStaySlotId() {
        return staySlotId;
    }

    public StayEndTime getStayEndTime() {
        return stayEndTime;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void addStayEndTime(StayEndTime stayEndTime) {
        Validate.notNull(stayEndTime);
        if(stayEndTime.toDate().isBefore(stayStartTime.toDate())){
            throw new IllegalArgumentException("StayEndTime can't be before StayStartTime");
        }

        this.stayEndTime = stayEndTime;
    }

    @Override
    public boolean sameIdentityAs(final Stay other) {
        return other != null && id.sameValueAs(other.id);
    }

    /**
     * @param object to compare
     * @return True if they have the same identity
     */
    @Override
    public boolean equals(final Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        final Stay other = (Stay) object;
        return sameIdentityAs(other);
    }

    /**
     * @return Hash code of id
     */
    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return id.toString();
    }

    public StayDTO toDTO() {
        StayDTO dto = new StayDTO();
        dto.stayId = this.id.toString();
        dto.parkId = this.stayParkId.toString();
        dto.stayStartTime = this.stayStartTime.toString();
        dto.stayEndTime = this.stayEndTime.toString();
        dto.vehicle = vehicle.toDto();
        return dto;
    }

}
