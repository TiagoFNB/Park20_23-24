package park20.Customer_Microservice.domain.Customer;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.Validate;
import park20.Customer_Microservice.shared.AttributeEncryptor;
import park20.Customer_Microservice.shared.ValueObject;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;

@Embeddable
public final class CustomerEmail implements ValueObject<CustomerEmail> {

    private static final String emailPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    @JsonProperty
    @Column(nullable = false, unique = true)
    @Convert(converter = AttributeEncryptor.class)
    private String email;

    protected CustomerEmail() {}

    /**
     * Constructor.
     *
     * @param email Designation string.
     */
    public CustomerEmail(final String email) {
        Validate.notNull(email);
        Validate.notEmpty(email);
        Validate.matchesPattern(email, emailPattern);
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerEmail other = (CustomerEmail) o;

        return sameValueAs(other);
    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }

    @Override
    public boolean sameValueAs(CustomerEmail other) {
        return other != null && this.email.equals(other.email);
    }

    @Override
    public String toString() {
        return email;
    }
}
