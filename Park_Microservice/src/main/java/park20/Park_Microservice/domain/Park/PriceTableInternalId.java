package park20.Park_Microservice.domain.Park;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.Validate;
import park20.Park_Microservice.shared.ValueObject;

import javax.persistence.Column;
import java.util.UUID;

public class PriceTableInternalId implements ValueObject<PriceTableInternalId> {

    @JsonProperty
    @Column(unique = true)
    private String id;

    protected PriceTableInternalId() {
    }

    public PriceTableInternalId(final String id) {
        Validate.notNull(id);
        Validate.notEmpty(id);
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PriceTableInternalId other = (PriceTableInternalId) o;

        return sameValueAs(other);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean sameValueAs(PriceTableInternalId other) {
        return other != null && this.id.equals(other.id);
    }

    @Override
    public String toString() {
        return id;
    }

    public static PriceTableInternalId genNewId() {
        return new PriceTableInternalId(UUID.randomUUID().toString());
    }

}
