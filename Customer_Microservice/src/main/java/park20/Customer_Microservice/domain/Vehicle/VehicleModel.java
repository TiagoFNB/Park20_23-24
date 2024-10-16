package park20.Customer_Microservice.domain.Vehicle;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.Validate;
import park20.Customer_Microservice.shared.AttributeEncryptor;
import park20.Customer_Microservice.shared.ValueObject;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;

@Embeddable
public final class VehicleModel implements ValueObject<VehicleModel> {

    @JsonProperty
    @Column(nullable = false)
    @Convert(converter = AttributeEncryptor.class)
    private String model;

    protected VehicleModel() {}

    /**
     * Constructor.
     *
     * @param model Designation string.
     */
    public VehicleModel(final String model) {
        Validate.notNull(model);
        Validate.notEmpty(model);
        this.model = model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VehicleModel other = (VehicleModel) o;

        return sameValueAs(other);
    }

    @Override
    public int hashCode() {
        return model.hashCode();
    }

    @Override
    public boolean sameValueAs(VehicleModel other) {
        return other != null && this.model.equals(other.model);
    }

    @Override
    public String toString() {
        return model;
    }
}

