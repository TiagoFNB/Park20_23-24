package park20.Customer_Microservice.domain.Customer;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.Validate;
import park20.Customer_Microservice.shared.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.UUID;

@Embeddable
public final class PaymentMethodInternalId implements ValueObject<PaymentMethodInternalId> {

    @JsonProperty
    @Column(unique = true)
    private String id;

    protected PaymentMethodInternalId() {}

    /**
     * Constructor.
     *
     * @param id Id string.
     */
    public PaymentMethodInternalId(final String id) {
        Validate.notNull(id);
        Validate.notEmpty(id);
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PaymentMethodInternalId other = (PaymentMethodInternalId) o;

        return sameValueAs(other);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean sameValueAs(PaymentMethodInternalId other) {
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
    public static PaymentMethodInternalId genNewId() {
        return new PaymentMethodInternalId(UUID.randomUUID().toString());
    }
}