package park20.Customer_Microservice.domain.Vehicle;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.Validate;
import park20.Customer_Microservice.shared.AttributeEncryptor;
import park20.Customer_Microservice.shared.ValueObject;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;

@Embeddable
public final class VehicleLicensePlate implements ValueObject<VehicleLicensePlate> {

    private static final String platePattern = "^(([A-Z]{2}-\\d{2}-(\\d{2}|[A-Z]{2}))|(\\d{2}-(\\d{2}-[A-Z]{2}|[A-Z]{2}-\\d{2})))$";

    @JsonProperty
    @Column(nullable = false, unique = true)
    @Convert(converter = AttributeEncryptor.class)
    private String plate;

    protected VehicleLicensePlate() {}

    /**
     * Constructor.
     *
     * @param plate Designation string.
     */
    public VehicleLicensePlate(final String plate) {
        Validate.notNull(plate);
        Validate.notEmpty(plate);
        Validate.matchesPattern(plate, platePattern);
        this.plate = plate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VehicleLicensePlate other = (VehicleLicensePlate) o;

        return sameValueAs(other);
    }

    @Override
    public int hashCode() {
        return plate.hashCode();
    }

    @Override
    public boolean sameValueAs(VehicleLicensePlate other) {
        return other != null && this.plate.equals(other.plate);
    }

    @Override
    public String toString() {
        return plate;
    }
}

