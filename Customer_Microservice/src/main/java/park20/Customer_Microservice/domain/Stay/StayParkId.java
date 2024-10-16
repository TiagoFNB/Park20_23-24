package park20.Customer_Microservice.domain.Stay;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.Validate;
import park20.Customer_Microservice.shared.ValueObject;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.regex.Pattern;

@Embeddable
public final class StayParkId implements ValueObject<StayParkId> {

    @JsonProperty
    @Column(nullable = false)
    private String parkId;

    /**
     * Constructor.
     *
     * @param parkId ParkId string.
     */
    public StayParkId(final String parkId) {
        Validate.notNull(parkId);
        Validate.notEmpty(parkId, "The StayParkId must contain a string");

        this.parkId = parkId;
    }

    protected StayParkId() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StayParkId other = (StayParkId) o;

        return sameValueAs(other);
    }

    @Override
    public int hashCode() {
        return parkId.hashCode();
    }

    @Override
    public boolean sameValueAs(StayParkId other) {
        return other != null && this.parkId.equals(other.parkId);
    }

    @Override
    public String toString() {
        return parkId;
    }
}

