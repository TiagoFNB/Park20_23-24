package park20.Park_Microservice.domain.Park;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.Validate;
import park20.Park_Microservice.shared.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.UUID;

@Embeddable
public class ParkSpotInternalId implements ValueObject<ParkSpotInternalId> {

    @JsonProperty
    @Column(unique = true)
    private String id;

    protected ParkSpotInternalId() {
    }

    public ParkSpotInternalId(String id) {
        Validate.notNull(id);
        Validate.notEmpty(id);
        this.id = id;
    }


    public static ParkSpotInternalId genNewId() {
        return new ParkSpotInternalId(UUID.randomUUID().toString());
    }



    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return id;
    }


    @Override
    public boolean sameValueAs(ParkSpotInternalId other) {
        return false;
    }
}
