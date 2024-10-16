package park20.Customer_Microservice.domain.Vehicle;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.Validate;
import park20.Customer_Microservice.shared.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.UUID;

@Embeddable
public final class VehicleInternalId implements ValueObject<VehicleInternalId> {

    @JsonProperty
    @Column(unique = true)
    private String id;

    protected VehicleInternalId() {}

    /**
     * Constructor.
     *
     * @param id Id string.
     */
    public VehicleInternalId(final String id) {
        Validate.notNull(id);
        Validate.notEmpty(id);
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VehicleInternalId other = (VehicleInternalId) o;

        return sameValueAs(other);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean sameValueAs(VehicleInternalId other) {
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
    public static VehicleInternalId genNewId() {
        return new VehicleInternalId(UUID.randomUUID().toString());
    }
}
