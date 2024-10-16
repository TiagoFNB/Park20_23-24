package park20.Park_Microservice.domain.Vehicle;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.Validate;
import park20.Park_Microservice.shared.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public final class VehicleCategory implements ValueObject<VehicleCategory> {

    @JsonProperty
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private VehicleCategoryEnum category;

    protected VehicleCategory() {}

    /**
     * Constructor.
     *
     * @param category Designation string.
     */
    public VehicleCategory(final VehicleCategoryEnum category) {
        Validate.notNull(category);
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VehicleCategory other = (VehicleCategory) o;

        return sameValueAs(other);
    }

    @Override
    public int hashCode() {
        return category.hashCode();
    }

    @Override
    public boolean sameValueAs(VehicleCategory other) {
        return other != null && this.category.equals(other.category);
    }

    @Override
    public String toString() {
        return category.toString();
    }
}
