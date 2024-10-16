package park20.Customer_Microservice.domain.Vehicle;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.Validate;
import park20.Customer_Microservice.shared.AttributeEncryptor;
import park20.Customer_Microservice.shared.ValueObject;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;

@Embeddable
public final class VehicleBrand implements ValueObject<VehicleBrand> {

    @JsonProperty
    @Column(nullable = false)
    @Convert(converter = AttributeEncryptor.class)
    private String brand;

    protected VehicleBrand() {}

    /**
     * Constructor.
     *
     * @param brand Designation string.
     */
    public VehicleBrand(final String brand) {
        Validate.notNull(brand);
        Validate.notEmpty(brand);
        this.brand = brand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VehicleBrand other = (VehicleBrand) o;

        return sameValueAs(other);
    }

    @Override
    public int hashCode() {
        return brand.hashCode();
    }

    @Override
    public boolean sameValueAs(VehicleBrand other) {
        return other != null && this.brand.equals(other.brand);
    }

    @Override
    public String toString() {
        return brand;
    }
}

