package park20.Customer_Microservice.domain.Stay;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.Validate;
import park20.Customer_Microservice.shared.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;


@Embeddable
public class StayEndTime implements ValueObject<StayEndTime> {

    @JsonProperty
    @Column
    private LocalDateTime  stayEndTime;

    /**
     * Constructor.
     *
     * @param time LocalDateTime.
     */
    public StayEndTime(LocalDateTime  time) {
        Validate.notNull(time);
        this.stayEndTime = time;
    }

    protected StayEndTime() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StayEndTime other = (StayEndTime) o;

        return sameValueAs(other);
    }

    @Override
    public int hashCode() {
        return stayEndTime.hashCode();
    }

    @Override
    public boolean sameValueAs(StayEndTime other) {
        return other != null && this.stayEndTime.equals(other.stayEndTime);
    }

    @Override
    public String toString() {
        return stayEndTime.toString();
    }

    public LocalDateTime toDate(){
        return stayEndTime;
    }

    public String idString() {
        return stayEndTime.toString();
    }
}
