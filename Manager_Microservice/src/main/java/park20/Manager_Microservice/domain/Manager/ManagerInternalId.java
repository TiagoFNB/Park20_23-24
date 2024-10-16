package park20.Manager_Microservice.domain.Manager;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.Validate;
import park20.Manager_Microservice.shared.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.UUID;

@Embeddable
public final class ManagerInternalId implements ValueObject<ManagerInternalId> {

    @JsonProperty
    @Column(unique = true)
    private String id;

    protected ManagerInternalId() {}

    /**
     * Constructor.
     *
     * @param id Id string.
     */
    public ManagerInternalId(final String id) {
        Validate.notNull(id);
        Validate.notEmpty(id);
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ManagerInternalId other = (ManagerInternalId) o;

        return sameValueAs(other);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean sameValueAs(ManagerInternalId other) {
        return other != null && this.id.equals(other.id);
    }

    @Override
    public String toString() {
        return id;
    }

    /**
     * Generates a new Id
     * @return SandwichInternalId
     */
    public static ManagerInternalId genNewId() {
        return new ManagerInternalId(UUID.randomUUID().toString());
    }
}