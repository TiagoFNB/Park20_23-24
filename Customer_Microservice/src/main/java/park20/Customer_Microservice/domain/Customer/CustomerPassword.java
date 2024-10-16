package park20.Customer_Microservice.domain.Customer;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.Validate;
import park20.Customer_Microservice.shared.AttributeEncryptor;
import park20.Customer_Microservice.shared.ValueObject;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;

@Embeddable
public final class CustomerPassword implements ValueObject<CustomerPassword> {

    // Password has to contain letters and numbers AND has to be at least 6 chars long
    private static final String passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d).{6,}$";

    @JsonProperty
    @Column(nullable = false)
    @Convert(converter = AttributeEncryptor.class)
    private String pass;

    protected CustomerPassword() {}

    /**
     * Constructor.
     *
     * @param password Designation string.
     */
    public CustomerPassword(final String password) {
        Validate.notNull(password);
        Validate.notEmpty(password);
        Validate.matchesPattern(password, passwordPattern);
        this.pass = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerPassword other = (CustomerPassword) o;

        return sameValueAs(other);
    }

    @Override
    public int hashCode() {
        return pass.hashCode();
    }

    @Override
    public boolean sameValueAs(CustomerPassword other) {
        return other != null && this.pass.equals(other.pass);
    }

    @Override
    public String toString() {
        return pass;
    }
}
