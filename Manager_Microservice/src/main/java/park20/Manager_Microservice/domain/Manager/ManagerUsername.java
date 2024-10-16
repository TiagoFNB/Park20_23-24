package park20.Manager_Microservice.domain.Manager;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.Validate;
import park20.Manager_Microservice.shared.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public final class ManagerUsername implements ValueObject<ManagerUsername> {

    private static final String usernamePattern = "^.{6,}$";

    @JsonProperty
    @Column(nullable = false, unique = true)
    private String username;

    protected ManagerUsername() {}

    /**
     * Constructor.
     *
     * @param username Designation string.
     */
    public ManagerUsername(final String username) {
        Validate.notNull(username);
        Validate.notEmpty(username);
        Validate.matchesPattern(username, usernamePattern);
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ManagerUsername other = (ManagerUsername) o;

        return sameValueAs(other);
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

    @Override
    public boolean sameValueAs(ManagerUsername other) {
        return other != null && this.username.equals(other.username);
    }

    @Override
    public String toString() {
        return username;
    }
}
