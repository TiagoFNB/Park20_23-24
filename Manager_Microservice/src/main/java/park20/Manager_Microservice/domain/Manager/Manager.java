package park20.Manager_Microservice.domain.Manager;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.apache.commons.lang3.Validate;
import park20.Manager_Microservice.dto.ManagerDTO;
import park20.Manager_Microservice.shared.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.EAGER;

@javax.persistence.Entity
@Table(name = "Manager")
public class Manager implements Entity<Manager> {

    @EmbeddedId
    @JsonUnwrapped
    private ManagerInternalId id;

    @Embedded
    @JsonUnwrapped
    private ManagerPassword pass;

    @Embedded
    @JsonUnwrapped
    private ManagerUsername username;

    @JsonProperty
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CustomerLanguagesEnum language;

    @ElementCollection(fetch = EAGER)
    @Column(nullable = false)
    private List<String> parks;

    protected Manager() {}

    public Manager(final ManagerInternalId id, final ManagerPassword pass, final ManagerUsername username) {
        Validate.notNull(id, "CustomerInternalId is required");
        Validate.notNull(pass, "CustomerPassword is required");
        Validate.notNull(username, "CustomerUsername is required");


        this.id = id;
        this.pass = pass;
        this.username = username;
        this.language = CustomerLanguagesEnum.en;
        this.parks = new ArrayList<>();
    }

    public ManagerInternalId getId() {
        return id;
    }

    public ManagerUsername getUsername() {
        return username;
    }

    public ManagerPassword getPass() {
        return pass;
    }

    public CustomerLanguagesEnum getLanguage() {
        return language;
    }

    public List<String> getParks() {return this.parks;};

    public void changeLanguage(CustomerLanguagesEnum lang) {
        this.language = lang;
    }

    public void emptyParks() {
        this.parks = new ArrayList<>();
    }

    public void addPark(String parkId) {
        this.parks.add(parkId);
    }

    @Override
    public boolean sameIdentityAs(final Manager other) {
        return other != null && id.sameValueAs(other.id);
    }

    /**
     * @param object to compare
     * @return True if they have the same identity
     */
    @Override
    public boolean equals(final Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        final Manager other = (Manager) object;
        return sameIdentityAs(other);
    }

    /**
     * @return Hash code of id
     */
    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return id.toString();
    }

    public ManagerDTO toDto() {
        ManagerDTO dto = new ManagerDTO();
        dto.id = id.toString();
        dto.username = username.toString();
        dto.language = language.toString();
        return dto;
    }

}
