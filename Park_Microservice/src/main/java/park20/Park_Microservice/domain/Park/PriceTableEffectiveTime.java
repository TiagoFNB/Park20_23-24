package park20.Park_Microservice.domain.Park;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.Validate;
import park20.Park_Microservice.shared.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;



@Embeddable
public class PriceTableEffectiveTime implements ValueObject<PriceTableEffectiveTime> {

    @JsonProperty
    @Column(nullable = false)
    private LocalDate effectiveTime ;

    /**
     * Constructor.
     *
     * @param time LocalDate.
     */
    public PriceTableEffectiveTime(LocalDate time, Boolean bootstrap) {
        if(!bootstrap) {
            Validate.notNull(time);
            if (time.isEqual(LocalDate.now()) || time.isBefore(LocalDate.now())) {
                throw new IllegalArgumentException("Effective time must not be today or before!");
            }
        }
        this.effectiveTime = time;
    }


    protected PriceTableEffectiveTime() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PriceTableEffectiveTime other = (PriceTableEffectiveTime) o;

        return sameValueAs(other);
    }

    @Override
    public int hashCode() {
        return effectiveTime.hashCode();
    }

    @Override
    public boolean sameValueAs(PriceTableEffectiveTime other) {
        return other != null && this.effectiveTime.equals(other.effectiveTime);
    }

    @Override
    public String toString() {
        return effectiveTime.toString();
    }

    public LocalDate toDate(){
        return effectiveTime;
    }

    public String idString() {
        return effectiveTime.toString();
    }
}
