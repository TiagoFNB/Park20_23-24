package park20.Customer_Microservice.domain.Stay;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.Validate;
import park20.Customer_Microservice.shared.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.UUID;

@Embeddable
public final class StayInternalId implements ValueObject<StayInternalId> {

    @JsonProperty
    @Column(unique = true)
    private String id;

    protected StayInternalId() {}

    /**
     * Constructor.
     *
     * @param id Id string.
     */
    public StayInternalId(final String id) {
        Validate.notNull(id);
        Validate.notEmpty(id);
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StayInternalId other = (StayInternalId) o;

        return sameValueAs(other);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean sameValueAs(StayInternalId other) {
        return other != null && this.id.equals(other.id);
    }

    @Override
    public String toString() {
        return id;
    }

    /**
     * Generates a new Id
     * @return VehicleInternalId
     */
    public static StayInternalId genNewId() {
        return new StayInternalId(UUID.randomUUID().toString());
    }
}
