package park20.Manager_Microservice.domain.Manager;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.Validate;
import park20.Manager_Microservice.shared.AttributeEncryptor;
import park20.Manager_Microservice.shared.ValueObject;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;

@Embeddable
public final class ManagerPassword implements ValueObject<ManagerPassword> {

    // Password has to contain letters and numbers AND has to be at least 6 chars long
    private static final String passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d).{6,}$";

    @JsonProperty
    @Column(nullable = false)
    @Convert(converter = AttributeEncryptor.class)
    private String pass;

    protected ManagerPassword() {}

    /**
     * Constructor.
     *
     * @param password Designation string.
     */
    public ManagerPassword(final String password) {
        Validate.notNull(password);
        Validate.notEmpty(password);
        Validate.matchesPattern(password, passwordPattern);
        this.pass = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ManagerPassword other = (ManagerPassword) o;

        return sameValueAs(other);
    }

    @Override
    public int hashCode() {
        return pass.hashCode();
    }

    @Override
    public boolean sameValueAs(ManagerPassword other) {
        return other != null && this.pass.equals(other.pass);
    }

    @Override
    public String toString() {
        return pass;
    }
}
