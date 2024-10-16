package park20.Park_Microservice.domain.Park;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import park20.Park_Microservice.domain.Vehicle.VehicleCategoryEnum;
import park20.Park_Microservice.dto.PriceTableDTO;
import park20.Park_Microservice.dto.PriceTableFractionDTO;
import park20.Park_Microservice.dto.PriceTablePeriodDTO;
import park20.Park_Microservice.dto.RemainingDTO;
import park20.Park_Microservice.shared.ValueObject;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="PriceTablePeriod")
public class PriceTablePeriod implements ValueObject<PriceTablePeriod> {

    @EmbeddedId
    @JsonUnwrapped
    private PriceTablePeriodInternalId id;

    @JsonProperty
    @Column
    public LocalTime startTime;

    @JsonProperty
    @Column
    public LocalTime endTime;

    @ManyToOne
    @JoinColumn(name = "price_table_id", referencedColumnName="id", nullable = false)
    public PriceTable priceTable;

    @ElementCollection
    @CollectionTable(
            name="PriceTablePeriodFraction",
            joinColumns=@JoinColumn(name="PriceTablePeriodId")
    )
    public List<PriceTableFraction> fractions;

    protected PriceTablePeriod() {
    }

    public PriceTablePeriod(PriceTablePeriodInternalId id,LocalTime startTime, LocalTime endTime, ArrayList<PriceTableFraction> fractions) {
        this.id=id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.fractions = fractions;
    }

    public PriceTablePeriodDTO toDto() {
        PriceTablePeriodDTO dto = new PriceTablePeriodDTO();
        dto.id = id.toString();
        dto.startTime = startTime.toString();
        dto.endTime =endTime.toString();
        ArrayList<PriceTableFractionDTO> ar = new ArrayList<>();
        for(PriceTableFraction fraction: fractions){
            PriceTableFractionDTO priceTableFractionDTO = fraction.toDto();
            ar.add(priceTableFractionDTO);
        }
        dto.fractions = ar.toArray(new PriceTableFractionDTO[ar.size()]);

        return dto;
    }

    public PriceTable getPriceTable() {
        return priceTable;
    }

    public void setPriceTable(PriceTable customer) {
        this.priceTable = customer;
    }

    public RemainingDTO getPriceFromPeriod(LocalTime startTime, Duration remainingDuration, VehicleCategoryEnum category) {
        Duration durationSpentInPeriod = Duration.between(startTime,this.endTime);
        RemainingDTO remaining = new RemainingDTO();
        remaining.total = 0.0;
        remaining.remainingDuration = remainingDuration;

        for(int i = 0; i < this.fractions.size(); i++) {
            Duration fractionDuration = Duration.ofMinutes(15);

            durationSpentInPeriod = durationSpentInPeriod.minus(fractionDuration);
            remainingDuration = remainingDuration.minus(fractionDuration);

            PriceTableFraction fraction = this.fractions.get(i);

            remaining.total  =  remaining.total  + fraction.getPrice(category);

            if(durationSpentInPeriod.isNegative() || remainingDuration.isNegative() || durationSpentInPeriod.isZero() || remainingDuration.isZero()) {
                remaining.remainingDuration = remainingDuration;
                return remaining;
            }
        }

        //For the last fraction form now on
        while(true) {
            Duration fractionDuration = Duration.ofMinutes(15);

            durationSpentInPeriod = durationSpentInPeriod.minus(fractionDuration);
            remainingDuration = remainingDuration.minus(fractionDuration);

            PriceTableFraction fraction = this.fractions.get(this.fractions.size()-1);

            remaining.total  =  remaining.total  + fraction.getPrice(category);

            if(durationSpentInPeriod.isNegative() || remainingDuration.isNegative() || durationSpentInPeriod.isZero() || remainingDuration.isZero()) {
                remaining.remainingDuration = remainingDuration;
                return remaining;
            }
        }
    }

    public Duration getLastFractionDuration() {
        Duration totalPeriodDuration = Duration.between(startTime,endTime).abs();

        Duration discount = Duration.ofMinutes((this.fractions.size()-1) *15);

        return totalPeriodDuration.minus(discount);
    }

    // Function to check if a LocalTime is between two other LocalTime instances
    public boolean isBetween(LocalTime timeToCheck) {
        if (endTime.isBefore(startTime)) {
            // Case where the time range spans midnight
            return !timeToCheck.isBefore(startTime) || !timeToCheck.isAfter(endTime);
        } else {
            // Standard case
            return !timeToCheck.isBefore(startTime) && !timeToCheck.isAfter(endTime);
        }
    }

    @Override
    public boolean sameValueAs(PriceTablePeriod other) {
        return false;
    }
}
