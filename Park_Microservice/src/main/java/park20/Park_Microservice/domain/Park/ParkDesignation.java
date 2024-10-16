package park20.Park_Microservice.domain.Park;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.Validate;
import park20.Park_Microservice.shared.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public final class ParkDesignation implements ValueObject<ParkDesignation> {


    @JsonProperty
    @Column(nullable = false, unique = true)
    private String designation;

    protected ParkDesignation() {}

    /**
     * Constructor.
     *
     * @param id Designation string.
     */
    public ParkDesignation(final String id) {
        Validate.notNull(id);
        Validate.notEmpty(id);
        this.designation = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParkDesignation other = (ParkDesignation) o;

        return sameValueAs(other);
    }

    @Override
    public int hashCode() {
        return designation.hashCode();
    }

    @Override
    public boolean sameValueAs(ParkDesignation other) {
        return other != null && this.designation.equals(other.designation);
    }

    @Override
    public String toString() {
        return designation;
    }
}

