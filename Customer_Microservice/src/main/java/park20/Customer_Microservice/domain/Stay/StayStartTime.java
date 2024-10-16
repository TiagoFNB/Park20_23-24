package park20.Customer_Microservice.domain.Stay;

import com.fasterxml.jackson.annotation.JsonProperty;


import org.apache.commons.lang3.Validate;
import park20.Customer_Microservice.shared.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDateTime;
import java.util.Date;


@Embeddable
public class StayStartTime implements ValueObject<StayStartTime> {

    @JsonProperty
    @Column(nullable = false)
    private LocalDateTime stayStartTime ;

    /**
     * Constructor.
     *
     * @param time LocalDateTime.
     */
    public StayStartTime(LocalDateTime time) {
        Validate.notNull(time);
        this.stayStartTime = time;
    }

    protected StayStartTime() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StayStartTime other = (StayStartTime) o;

        return sameValueAs(other);
    }

    @Override
    public int hashCode() {
        return stayStartTime.hashCode();
    }

    @Override
    public boolean sameValueAs(StayStartTime other) {
        return other != null && this.stayStartTime.equals(other.stayStartTime);
    }

    @Override
    public String toString() {
        return stayStartTime.toString();
    }

    public LocalDateTime toDate(){
        return stayStartTime;
    }

    public String idString() {
        return stayStartTime.toString();
    }
}
