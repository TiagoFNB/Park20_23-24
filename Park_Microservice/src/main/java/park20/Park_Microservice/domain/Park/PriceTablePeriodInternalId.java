package park20.Park_Microservice.domain.Park;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.Validate;
import park20.Park_Microservice.shared.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.UUID;

@Embeddable
public class PriceTablePeriodInternalId implements ValueObject<PriceTablePeriodInternalId> {

    @JsonProperty
    @Column(unique = true)
    private String id;

    protected PriceTablePeriodInternalId() {
    }

    public PriceTablePeriodInternalId(String id) {
        Validate.notNull(id);
        Validate.notEmpty(id);
        this.id = id;
    }


    public static PriceTablePeriodInternalId genNewId() {
        return new PriceTablePeriodInternalId(UUID.randomUUID().toString());
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
    public boolean sameValueAs(PriceTablePeriodInternalId other) {
        return false;
    }
}
