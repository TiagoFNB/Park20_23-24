package park20.Customer_Microservice.domain.Stay;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.Validate;
import park20.Customer_Microservice.shared.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public final class StaySlotId implements ValueObject<StaySlotId> {

    @JsonProperty
    @Column(nullable = false)
    private String slotId;

    /**
     * Constructor.
     *
     * @param parkId ParkId string.
     */
    public StaySlotId(final String parkId) {
        Validate.notNull(parkId);
        Validate.notEmpty(parkId, "The StaySlotId must contain a string");

        this.slotId = parkId;
    }

    protected StaySlotId() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StaySlotId other = (StaySlotId) o;

        return sameValueAs(other);
    }

    @Override
    public int hashCode() {
        return slotId.hashCode();
    }

    @Override
    public boolean sameValueAs(StaySlotId other) {
        return other != null && this.slotId.equals(other.slotId);
    }

    @Override
    public String toString() {
        return slotId;
    }
}

