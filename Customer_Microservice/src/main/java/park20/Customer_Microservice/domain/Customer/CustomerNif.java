package park20.Customer_Microservice.domain.Customer;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.Validate;
import park20.Customer_Microservice.shared.AttributeEncryptor;
import park20.Customer_Microservice.shared.ValueObject;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;

@Embeddable
public final class CustomerNif implements ValueObject<CustomerNif> {

    private static final String nifPattern = "\\d{9}";

    @JsonProperty
    @Column(nullable = false, unique = true)
    @Convert(converter = AttributeEncryptor.class)
    private String nif;

    protected CustomerNif() {}

    /**
     * Constructor.
     *
     * @param nif Designation string.
     */
    public CustomerNif(final String nif) {
        Validate.notNull(nif);
        Validate.notEmpty(nif);
        Validate.matchesPattern(nif, nifPattern);
        this.nif = nif;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerNif other = (CustomerNif) o;

        return sameValueAs(other);
    }

    @Override
    public int hashCode() {
        return nif.hashCode();
    }

    @Override
    public boolean sameValueAs(CustomerNif other) {
        return other != null && this.nif.equals(other.nif);
    }

    @Override
    public String toString() {
        return nif;
    }
}
