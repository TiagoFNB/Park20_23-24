package park20.Customer_Microservice.domain.Customer;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.Validate;
import park20.Customer_Microservice.shared.AttributeEncryptor;
import park20.Customer_Microservice.shared.ValueObject;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;

@Embeddable
public final class CustomerName implements ValueObject<CustomerName> {

    @JsonProperty
    @Column(nullable = false)
    @Convert(converter = AttributeEncryptor.class)
    private String name;

    protected CustomerName() {}

    /**
     * Constructor.
     *
     * @param name Designation string.
     */
    public CustomerName(final String name) {
        Validate.notNull(name);
        Validate.notEmpty(name);
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerName other = (CustomerName) o;

        return sameValueAs(other);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean sameValueAs(CustomerName other) {
        return other != null && this.name.equals(other.name);
    }

    @Override
    public String toString() {
        return name;
    }
}
